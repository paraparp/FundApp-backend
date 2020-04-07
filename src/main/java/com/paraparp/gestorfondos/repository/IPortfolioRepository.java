package com.paraparp.gestorfondos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paraparp.gestorfondos.model.Portfolio;



@Repository
public interface IPortfolioRepository extends JpaRepository<Portfolio, Long> {

	

}
