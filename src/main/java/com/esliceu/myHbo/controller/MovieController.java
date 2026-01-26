package com.esliceu.myHbo.controller;

import com.esliceu.myHbo.model.Movie;
import com.esliceu.myHbo.model.MovieCast;
import com.esliceu.myHbo.model.MovieCrew;
import com.esliceu.myHbo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies/search")
    public String searchPage() {
        return "movieSearch";
    }

    @GetMapping("/api/movies/autocomplete")
    @ResponseBody
    public List<String> autocomplete(@RequestParam String term) {
        return movieService.getAutocompleteTitles(term);
    }

    @GetMapping("/movies/results")
    public String searchResults(@RequestParam(defaultValue = "title") String searchType,
                                @RequestParam(required = false) String searchTerm,
                                Model model) {
        List<Movie> movies = movieService.searchMovies(searchType, searchTerm);
        model.addAttribute("movies", movies);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);
        return "movieResults";
    }

    @GetMapping("/movies/info")
    public String movieInfo(@RequestParam Integer id, Model model) {

        Optional<Movie> movieOpt = movieService.findByIdWithAllInfo(id);

        if (movieOpt.isEmpty()) {
            model.addAttribute("error", "Pelicula no encontrada");
            return "error";
        }

        Movie movie = movieOpt.get();

        model.addAttribute("movie", movie);
        model.addAttribute("cast", movie.getCast());
        model.addAttribute("crew", movie.getCrew());
        model.addAttribute("genres", movie.getGenres());
        model.addAttribute("companies", movie.getProductionCompanies());
        model.addAttribute("countries", movie.getCountries());
        model.addAttribute("keywords", movie.getKeywords());
        model.addAttribute("languages", movie.getMovieLanguages());

        return "movieInfo";
    }

}
