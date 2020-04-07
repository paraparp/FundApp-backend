package com.paraparp.gestorfondos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.paraparp.gestorfondos.model.Symbol;



@Repository
public interface ISymbolRepository extends JpaRepository<Symbol, Long> {

	

}
