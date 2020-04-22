package com.paraparp.gestorfondos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.dto.LotDTO;


@Service
public interface ILotService {
	
	
	public LotDTO findById(Long id);
	
	public LotDTO save(LotDTO lot);
	
	public void deleteById(Long id);

	public List<LotDTO> findAll();


	public LotDTO update(LotDTO lot);

}
