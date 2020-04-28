package com.paraparp.gestorfondos.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paraparp.gestorfondos.model.dto.LotDTO;
import com.paraparp.gestorfondos.model.entity.Lot;
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
		return modelMapper.map(lot.get(), LotDTO.class);
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
		List<Lot> lotsBack = new ArrayList<Lot>();
		lotsBack = this.lotRepository.findAll();

		for (Lot lot : lotsBack) {
			listLots.add(this.modelMapper.map(lot, LotDTO.class));
		}
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

}
