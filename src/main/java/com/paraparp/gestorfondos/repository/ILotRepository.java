package com.paraparp.gestorfondos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.paraparp.gestorfondos.model.entity.Lot;
import com.paraparp.gestorfondos.model.entity.Portfolio;
import com.paraparp.gestorfondos.model.entity.Symbol;

@Repository
public interface ILotRepository extends JpaRepository<Lot, Long> {

	@Query("SELECT l FROM Lot l where l.symbol =?1 and l.portfolio.id =?2")
	public List<Lot> findBySymbolAndPortfolio(Symbol symbol, Long idPortfolio);

	public List<Lot> findByPortfolio(Portfolio portfolio);

}
