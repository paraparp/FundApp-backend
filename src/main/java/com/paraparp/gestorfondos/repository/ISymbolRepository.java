package com.paraparp.gestorfondos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.paraparp.gestorfondos.model.entity.Symbol;

@Repository
public interface ISymbolRepository extends JpaRepository<Symbol, Long> {

	@Query("SELECT s FROM Symbol s where s.isin =?1 ")
	public Symbol findByIsin(String isin);

}
