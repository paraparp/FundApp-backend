package com.paraparp.gestorfondos.service.imp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paraparp.gestorfondos.model.dto.LotDTO;
import com.paraparp.gestorfondos.model.entity.Lot;
import com.paraparp.gestorfondos.model.entity.Symbol;
import com.paraparp.gestorfondos.repository.ILotRepository;
import com.paraparp.gestorfondos.service.ILotService;

@Service
public class LotService implements ILotService {

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ILotRepository lotRepository;

	@Override
	@Transactional(readOnly = true)
	public LotDTO findById(Long id) {

		Optional<Lot> lot = lotRepository.findById(id);
		if (lot.isPresent()) {
			return modelMapper.map(lot.get(), LotDTO.class);
		} else {
			throw new EntityNotFoundException("Credential with id " + id + " does not exists");
		}
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		lotRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<LotDTO> findAll() {

		List<LotDTO> listLots = new ArrayList<LotDTO>();
		List<Lot> lotsBack = this.lotRepository.findAll();
		lotsBack.forEach(lot -> listLots.add(this.modelMapper.map(lot, LotDTO.class)));
		return listLots;
	}

	@Override
	@Transactional
	public LotDTO save(LotDTO lotDTO) {

		Optional<Lot> lotOp = lotRepository.findById(lotDTO.getId());
		Lot lot = modelMapper.map(lotDTO, Lot.class);
		return modelMapper.map(lotRepository.save(lot), LotDTO.class);
	}

	@Override
	public LotDTO update(LotDTO lotDTO) {

		Lot lot = modelMapper.map(lotDTO, Lot.class);
		return modelMapper.map(lotRepository.save(lot), LotDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<LotDTO> findBySymbolAndPortfolio(Symbol symbol, Long idPortfolio) {

		List<Lot> lotsBack = lotRepository.findBySymbolAndPortfolio(symbol, idPortfolio);
		List<LotDTO> lotsDTO = new ArrayList<LotDTO>();
		lotsBack.forEach(lot -> lotsDTO.add(this.modelMapper.map(lot, LotDTO.class)));

		return lotsDTO;

	}

	@Override
	@Transactional(readOnly = true)
	public List<LotDTO> findBySymbolAndPortfolioAndBrokerAndType(Symbol symbol, Long idPortfolio, String broker,
			String type) {

		if (broker.isEmpty())
			broker = null;
		if (type.isEmpty())
			type = null;
		List<LotDTO> lotsDTO = new ArrayList<LotDTO>();
		List<Lot> lotsBack = lotRepository.findBySymbolAndPortfolioAndBrokerAndType(symbol, idPortfolio, broker, type);

		lotsBack.forEach(lot -> lotsDTO.add(this.modelMapper.map(lot, LotDTO.class)));

		return lotsDTO;

	}

	@Override
	public List<LotDTO> findBySymbolAndPortfolioBeforeDate(Symbol symbol, Long idPortfolio, LocalDate endDate) {

		List<Lot> lotsBack = lotRepository.findBySymbolAndPortfolio(symbol, idPortfolio);
		List<LotDTO> lotsDTO = new ArrayList<LotDTO>();
		lotsBack.forEach(lot -> {
			if (lot.getDate().isBefore(endDate.plusDays(1))) {
				lotsDTO.add(this.modelMapper.map(lot, LotDTO.class));}
		});
		
		return lotsDTO;
	}

}
