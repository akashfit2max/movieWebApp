package com.cognizant.moviecruiser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.moviecruiser.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	/**
	 * sysdate = the current/present date choose the movie whose status is active
	 * and within the date
	 * 
	 * @return
	 */
	@Query("select m from Movie m where active='Yes' and dateOfLaunch<=sysdate()")
	List<Movie> findAllCustomer();
}
