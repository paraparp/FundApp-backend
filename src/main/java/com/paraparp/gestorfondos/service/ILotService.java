package com.paraparp.gestorfondos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.dto.LotDTO;
import com.paraparp.gestorfondos.model.entity.Lot;


@Service
public interface ILotService {
	
	
	public Lot findById(Long id);
	
	public Lot save(Lot portfolio);
	
	public void deleteById(Long id);

	public List<Lot> findAll();

	public LotDTO save(LotDTO lot);


	public LotDTO update(LotDTO lot);

}
