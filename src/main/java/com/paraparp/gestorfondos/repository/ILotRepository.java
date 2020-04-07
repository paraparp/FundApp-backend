package com.paraparp.gestorfondos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.paraparp.gestorfondos.model.Lot;
import com.paraparp.gestorfondos.model.Portfolio;
import com.paraparp.gestorfondos.model.Symbol;



@Repository
public interface ILotRepository extends JpaRepository<Lot, Long> {

	public List<Lot> findBySymbolAndPortfolio(Symbol symbol, Portfolio portfolio);

}
