package com.paraparp.gestorfondos.service.imp;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paraparp.gestorfondos.model.dto.DailyCostDTO;
import com.paraparp.gestorfondos.model.dto.LotDTO;
import com.paraparp.gestorfondos.model.dto.PortfolioDTO;
import com.paraparp.gestorfondos.model.dto.SimpleLotDTO;
import com.paraparp.gestorfondos.model.dto.SymbolLotDTO;
import com.paraparp.gestorfondos.model.entity.Historical;
import com.paraparp.gestorfondos.model.entity.Lot;
import com.paraparp.gestorfondos.model.entity.Portfolio;
import com.paraparp.gestorfondos.model.entity.User;
import com.paraparp.gestorfondos.repository.ILotRepository;
import com.paraparp.gestorfondos.repository.IPortfolioRepository;
import com.paraparp.gestorfondos.repository.IUserRepository;
import com.paraparp.gestorfondos.service.IHistoricalService;
import com.paraparp.gestorfondos.service.IMorningStartService;
import com.paraparp.gestorfondos.service.IPortfolioService;
import com.paraparp.gestorfondos.service.ISymbolLotsService;

@Service
public class PortfolioService implements IPortfolioService {

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private IPortfolioRepository portfolioRepo;

	@Autowired
	private ILotRepository lotRepo;

	@Autowired
	private IUserRepository userRepo;

	@Autowired
	private ISymbolLotsService symbolLotsService;

	@Autowired
	private IMorningStartService msService;

	@Autowired
	private IHistoricalService historicalService;

	@Override
	@Transactional
	public void deleteById(Long id) {
		portfolioRepo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public PortfolioDTO findById(Long id) {

		Optional<Portfolio> portfolio = portfolioRepo.findById(id);
		return modelMapper.map(portfolio.get(), PortfolioDTO.class);
	}

	@Override
	@Transactional
	public PortfolioDTO save(PortfolioDTO portfolioDTO) {

		Portfolio portfolio = modelMapper.map(portfolioDTO, Portfolio.class);
		if (portfolioDTO.getIdUser() != null) {
			Optional<User> user = userRepo.findById(portfolioDTO.getIdUser());
			if (user.isPresent()) {
				portfolio.setUser(user.get());
			}
		}

		Portfolio saved = portfolioRepo.save(portfolio);
		return modelMapper.map(saved, PortfolioDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PortfolioDTO> findAll() {
		List<PortfolioDTO> listPortfolios = new ArrayList<PortfolioDTO>();

		List<Portfolio> portfoliosBack = this.portfolioRepo.findAll();
		for (Portfolio portfolio : portfoliosBack) {
			listPortfolios.add(this.modelMapper.map(portfolio, PortfolioDTO.class));
		}
		return listPortfolios;
	}

	@Override
	public List<PortfolioDTO> findByIdUser(Long userId) {

		User user = userRepo.getOne(userId);
		List<PortfolioDTO> listPortfolios = new ArrayList<PortfolioDTO>();
		List<Portfolio> portfoliosBack = new ArrayList<Portfolio>();
		portfoliosBack = this.portfolioRepo.findByUser(user);

		for (Portfolio portfolio : portfoliosBack) {
			listPortfolios.add(this.modelMapper.map(portfolio, PortfolioDTO.class));

		}
		return listPortfolios;
	}

	public List<LotDTO> findLotsByPorfolio(Long portfolioId) {

		Portfolio portfolio = portfolioRepo.findById(portfolioId).orElseThrow();
		List<LotDTO> listLots = new ArrayList<LotDTO>();
		List<Lot> lotsBack = new ArrayList<Lot>();
		lotsBack = this.lotRepo.findByPortfolio(portfolio);

		for (Lot lot : lotsBack) {
			listLots.add(this.modelMapper.map(lot, LotDTO.class));
		}
		return listLots;
	}

	@Override
	public List<String> listBrokersByPortfolio(Long idPortfolio) {

		Set<String> brokerList = new HashSet<String>();
		findLotsByPorfolio(idPortfolio).forEach(symbol -> brokerList.add(symbol.getBroker()));

		return new ArrayList<>(brokerList);
	}

	@Override
	public List<String> listTypesByPortfolio(Long idPortfolio) {

		Set<String> typeList = new HashSet<String>();
		findLotsByPorfolio(idPortfolio).forEach(lot -> typeList.add(lot.getSymbol().getType()));

		return new ArrayList<>(typeList);
	}

	public List<DailyCostDTO> findCostPortfolio(Long portfolioId) throws IOException, JSONException {

		LocalDate fromDate = this.findFirstTransactionDate(portfolioId).minusDays(14);
		LocalDate firstFriday = this.getFridayOfThisWeek(fromDate);
		Portfolio portfolio = portfolioRepo.findById(portfolioId).orElseThrow();
		Set<SimpleLotDTO> listLots = new HashSet<SimpleLotDTO>();
		List<Lot> lotsBack = new ArrayList<Lot>();
		lotsBack = this.lotRepo.findByPortfolio(portfolio);

		BigDecimal total = BigDecimal.ZERO;
		for (Lot lot : lotsBack) {
			total = total.add(lot.getPrice().multiply(lot.getVolume()));
			listLots.add(new SimpleLotDTO(lot.getDate(), lot.getPrice(), lot.getVolume(),
					lot.getPrice().multiply(lot.getVolume())));
		}

		List<DailyCostDTO> fridaysInRange = listOfFridaysFrom(firstFriday);

		for (DailyCostDTO dailyCost : fridaysInRange) {

			List<SymbolLotDTO> lits = symbolLotsService.findByPortfolioAndEndDate(portfolioId,
					dailyCost.getDate().toString());

			BigDecimal totalDay = BigDecimal.ZERO;
			BigDecimal totalValueDay = BigDecimal.ZERO;
			BigDecimal totalValueDayBond = BigDecimal.ZERO;

			for (SymbolLotDTO symbols : lits) {

				totalDay = totalDay.add(symbols.getCost());
				Historical hist = historicalService.findBySymbolAndDate(symbols.getSymbol(), dailyCost.getDate());
				if (hist != null)
					totalValueDay = totalValueDay.add(hist.getPrice().multiply(symbols.getVolume()));
				else
					totalValueDay = totalValueDay.add(msService.getLastPrice(symbols.getSymbol().getIsin()).multiply(symbols.getVolume()));
				
				if (!StringUtils.isEmpty(symbols.getSymbol().getType())
						&& symbols.getSymbol().getType().contains("Bond")) {
					totalValueDayBond = totalValueDayBond.add(symbols.getValue());
				}
			}

			dailyCost.setTotalCost(totalDay);
			dailyCost.setTotalGain(totalValueDay);
			dailyCost.setBondPercent(totalValueDayBond);

//			dailyCost.setBondPercent(
//					(totalValueDayBond == BigDecimal.ZERO || totalValueDay == BigDecimal.ZERO) ? BigDecimal.ZERO
//							: totalValueDayBond.divide(totalValueDay,4, RoundingMode.HALF_UP));
		}

		return fridaysInRange;
	}

	public String xRayPortfolio(Long idPortfolio) throws IOException, JSONException {

		List<SymbolLotDTO> listSymbolsPortfolio = symbolLotsService.findByPortfolio(idPortfolio);

		String urlXRay = "https://lt.morningstar.com/j2uwuwirpv/xraypdf/default.aspx?LanguageId=es-ES&PortfolioType=2&SecurityTokenList=";

		String idMSList = "";
		String values = "";
		for (SymbolLotDTO symbolLotDTO : listSymbolsPortfolio) {

			String idMS = msService.getMorningStarID(symbolLotDTO.getSymbol().getIsin()) + "]2]0]E0WWE$$ALL_1340%7C";

			idMSList += idMS;
		}
		idMSList = idMSList.substring(0, idMSList.length() - 3);
		for (SymbolLotDTO symbolLotDTO : listSymbolsPortfolio) {

			values += Integer.valueOf(
					symbolLotDTO.getLastPercentInPortfolio().multiply(new BigDecimal(10000)).intValue()) + "%7C";
		}
		values = values.substring(0, values.length() - 3);

		return urlXRay + idMSList + "&values=" + values + "&from=editholding";

	}

	/**
	 * crea una lista con todos los viernes de un rango
	 * 
	 * @return
	 */
	private List<DailyCostDTO> listOfFridaysFrom(LocalDate start) {

		LocalDate end = LocalDate.now();

		DayOfWeek dowOfStart = start.getDayOfWeek();
		int difference = DayOfWeek.FRIDAY.getValue() - dowOfStart.getValue();
		if (difference < 0)
			difference += 7;

		List<DailyCostDTO> fridaysInRange = new ArrayList<DailyCostDTO>();

		LocalDate currentFriday = start.plusDays(difference);
		do {
			fridaysInRange.add(new DailyCostDTO(currentFriday, BigDecimal.ZERO, BigDecimal.ZERO));
			currentFriday = currentFriday.plusDays(7);
		} while (currentFriday.isBefore(end));

		return fridaysInRange;
	}
	
	private LocalDate getFridayOfThisWeek(LocalDate date) {
		
		DayOfWeek dowOfStart = date.getDayOfWeek();
		int difference = DayOfWeek.FRIDAY.getValue() - dowOfStart.getValue();
		if (difference < 0)
			difference += 7;


		return date.plusDays(difference);
	}

	private LocalDate findFirstTransactionDate(Long idPortfolio) {

		List<LotDTO> listlots = this.findLotsByPorfolio(idPortfolio);
		LocalDate firstDate = LocalDate.now();
		for (LotDTO lot : listlots) {
			if (lot.getDate().isBefore(firstDate))
				firstDate = lot.getDate();
		}

		return firstDate;
	}
}
