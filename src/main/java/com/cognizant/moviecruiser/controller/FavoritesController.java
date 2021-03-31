package com.cognizant.moviecruiser.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.moviecruiser.model.Favorites;
import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.service.FavoritesService;
import com.cognizant.moviecruiser.service.MovieService;

@Controller
public class FavoritesController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
	@Autowired
	private FavoritesService favoritesService;
	@Autowired
	private MovieService movieService;

	@GetMapping("/add-to-favorites")
	public String addToFavorites(@RequestParam("movieId") int movieId, ModelMap map) {
		List<Movie> movieList = movieService.getMovieListCustomer();
		map.put("movieList", movieList);
		favoritesService.addToFavorites(movieId);
		return "movie-list-customer-notification";
	}

	@GetMapping("/favorites")
	public String favorites(ModelMap model) throws Exception {
		List<Favorites> favorites = favoritesService.getFavorites();
		if (favorites.size() == 0)
			return "favorites-empty";
		model.put("favorites", favorites);
		model.put("total", favorites.size());
		return "favorites";
	}

	@GetMapping("/delete")
	public String deleteItem(@RequestParam int favId, ModelMap model) {
		favoritesService.deleteFav(favId);
		List<Favorites> favorites = favoritesService.getFavorites();
		if (favorites.size() == 0)
			return "favorites-empty";
		model.put("favorites", favorites);
		model.put("total", favorites.size());
		return "favorites-notification";
	}

}
