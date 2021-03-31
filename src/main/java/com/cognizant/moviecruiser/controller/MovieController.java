package com.cognizant.moviecruiser.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.service.MovieService;

@Controller
public class MovieController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
	@Autowired
	private MovieService movieService;

	/**
	 * 
	 * @param model to show the list of movies in the db
	 * @return movie-list-page
	 * @throws Exception
	 */
	@GetMapping("/show-movie-list-admin")
	public String showMovieListAdmin(ModelMap model) throws Exception {
		List<Movie> movieList = movieService.getMovieListAdmin();
		model.put("movieList", movieList);
		return "movie-list-admin";
	}

	/**
	 * 
	 * @param model show the list of movie so that u can choose the movie that u
	 *              want to add in favourite
	 * @return customer movie page
	 * @throws Exception
	 */
	@GetMapping("/show-movie-list-customer")
	public String showMovieCustomer(ModelMap model) throws Exception {
		List<Movie> movieList = movieService.getMovieListCustomer();
		model.put("movieList", movieList);
		return "movie-list-customer";
	}

	@GetMapping("/edit-movie")
	public String showEditMovie(ModelMap model, @RequestParam("movieId") int id) throws ClassNotFoundException {
		Movie movie = movieService.getMovie(id);
		model.put("movie", movie);
		return "edit-movie";
	}

	@PostMapping("/edit-movie")
	public String editMovie(@ModelAttribute Movie movie, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "edit-movie";
		}
		if (movie.getHasTeaser() == null)
			movie.setHasTeaser("No");
		movieService.editMovie(movie);
		return "edit-movie-status";
	}

	@RequestMapping("/add-movie-list-db")
	public String addMovie(@ModelAttribute("movie") Movie movie, BindingResult result) {
		return "add-movie";
	}

	@RequestMapping(value = "/add-movie-indb", method = RequestMethod.POST)
	public String addMovieDb(@ModelAttribute("movie") Movie movie, BindingResult result) {
		if (result.hasErrors()) {
			return "add-movie";
		}
		Movie mv = movieService.addMovie(movie);
		if (mv != null) {
			return "add-movie-status";
		}
		return "add-movie";
	}

}
