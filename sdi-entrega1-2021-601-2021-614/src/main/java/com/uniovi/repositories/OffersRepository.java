package com.uniovi.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;


public interface OffersRepository extends CrudRepository<Offer, Long> {
	
	@Query("SELECT r FROM Offer r WHERE r.user = ?1 ORDER BY r.id ASC ")
	Page<Offer> findAllByUser(Pageable pageable, User user);

	

}
