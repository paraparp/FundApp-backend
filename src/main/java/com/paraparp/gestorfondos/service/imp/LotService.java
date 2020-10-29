package com.paraparp.gestorfondos.service.imp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

		List<LotDTO> listLots = new ArrayList<>();
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
		List<LotDTO> lotsDTO = new ArrayList<>();
		lotsBack.forEach(lot -> lotsDTO.add(this.modelMapper.map(lot, LotDTO.class)));

		return lotsDTO;

	}

	@Override
	@Transactional(readOnly = true)
	public List<LotDTO> findBySymbolAndPortfolioAndBrokerAndType(Symbol symbol, Long idPortfolio, String broker,
			String type) {
		
		if (type != null && broker.isEmpty())
			broker = null;
		if (type != null && type.isEmpty())
			type = null;
		List<LotDTO> lotsDTO = new ArrayList<>();
		List<Lot> lotsBack = lotRepository.findBySymbolAndPortfolioAndBrokerAndType(symbol, idPortfolio, broker, type);

		lotsBack.forEach(lot -> lotsDTO.add(this.modelMapper.map(lot, LotDTO.class)));

		return lotsDTO;

	}

	@Override
	public List<LotDTO> findBySymbolAndPortfolioBeforeDate(Symbol symbol, Long idPortfolio, LocalDate endDate) {

		List<Lot> lotsBack = lotRepository.findBySymbolAndPortfolio(symbol, idPortfolio);
		
		List<LotDTO> lotsDTO = new ArrayList<>();
		lotsBack.forEach(lot -> {
			if (lot.getDate().isBefore(endDate.plusDays(1))) {//TODO
				lotsDTO.add(this.modelMapper.map(lot, LotDTO.class));
			}
		});

		return lotsDTO;
	}

	// TODO using specification
	public List<Lot> specFindBySymbolAndPortfolioAndBrokerAndType(Symbol symbol, Long idPortfolio,
			Optional<String> broker, Optional<String> type) {

		Specification<Lot> spectBrokerLot = new Specification<Lot>() {

			@Override
			public Predicate toPredicate(Root<Lot> root, CriteriaQuery<?> query, CriteriaBuilder cr) {

				if (broker.isPresent()) {
					return cr.equal(cr.lower(root.get("broker")), "%" + broker.get() + "%");
				} else {
					return cr.isTrue(cr.literal(true));
				}
			}
		};

		Specification<Lot> spectTypeLot = new Specification<Lot>() {

			@Override
			public Predicate toPredicate(Root<Lot> root, CriteriaQuery<?> query, CriteriaBuilder cr) {

				if (broker.isPresent()) {
					return cr.like(cr.lower(root.get("type")), "%" + type.get() + "%");
				} else {
					return cr.isTrue(cr.literal(true));
				}
			}
		};
		
		Specification<Lot> both = spectBrokerLot.and(spectTypeLot);

		return this.lotRepository.findAll(both);

	}

}
