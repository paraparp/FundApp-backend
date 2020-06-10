package com.paraparp.gestorfondos.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.paraparp.gestorfondos.model.entity.Historical;
import com.paraparp.gestorfondos.model.entity.Symbol;

@Repository
public interface IHistoricalRepository extends JpaRepository<Historical, Long> {

	@Query("SELECT h FROM Historical h where h.symbol =?1 and h.enddate =?2")
	public Historical findBySymbolAndDate(Symbol symbol, LocalDate ld);
	
	@Query("SELECT h FROM Historical h where h.symbol.isin =?1 ")
	public List<Historical> findByIsin(String isin);

}
