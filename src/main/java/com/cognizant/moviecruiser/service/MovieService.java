package com.cognizant.moviecruiser.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.repository.MovieRepository;

@Service
public class MovieService {
	@Autowired
	MovieRepository movieRepository;

//	select *from movie 
	@Transactional
	public List<Movie> getMovieListAdmin() {
		return movieRepository.findAll();
	}

	@Transactional
	public List<Movie> getMovieListCustomer() {
		return movieRepository.findAllCustomer();
	}

	@Transactional
	public Movie getMovie(int id) {
		return movieRepository.getOne(id);
	}

	@Transactional
	public void editMovie(Movie movie) {
		Movie m = movieRepository.getOne(movie.getId());
		m.setTitle(movie.getTitle());
		m.setBoxOffice(movie.getBoxOffice());
		m.setActive(movie.getActive());
		m.setDateOfLaunch(movie.getDateOfLaunch());
		m.setGenre(movie.getGenre());
		m.setHasTeaser(movie.getHasTeaser());
		movieRepository.save(m);
	}

	public Movie addMovie(Movie movie) {
		Movie m = movieRepository.save(movie);
		return movie;

	}

}
