package com.paraparp.gestorfondos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.paraparp.gestorfondos.model.entity.User;



@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	
	@Query("select u from User u where u.username=?1 or u.email=?1")
	public User findByUsernameAndEmail(String username);

}
