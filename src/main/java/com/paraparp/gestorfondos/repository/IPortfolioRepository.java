package com.paraparp.gestorfondos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paraparp.gestorfondos.model.dto.PortfolioDTO;
import com.paraparp.gestorfondos.model.entity.Portfolio;
import com.paraparp.gestorfondos.model.entity.User;

@Repository
public interface IPortfolioRepository extends JpaRepository<Portfolio, Long> {

	public Portfolio save(PortfolioDTO portfolio);

	public List<Portfolio> findByUser(User user);

}
