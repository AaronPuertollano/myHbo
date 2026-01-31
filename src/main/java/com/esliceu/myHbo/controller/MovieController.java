package com.esliceu.myHbo.controller;

import com.esliceu.myHbo.model.Movie;
import com.esliceu.myHbo.model.MovieCast;
import com.esliceu.myHbo.model.MovieCrew;
import com.esliceu.myHbo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
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

    @PostMapping("/movies/delete")
    public String deleteMovie(@RequestParam Integer id) {
        movieService.delete(id);
        return "redirect:/movies/search";
    }

    @GetMapping("/movies/create")
    public String createMoviePage() {
        return "movieCreate";
    }

    @PostMapping("/movies/create")
    public String createMovie(
            @RequestParam String title,
            @RequestParam(required = false) Integer budget,
            @RequestParam(required = false) String homepage,
            @RequestParam(required = false) String overview,
            @RequestParam(required = false) BigDecimal popularity,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate releaseDate,
            @RequestParam(required = false) Long revenue,
            @RequestParam(required = false) Integer runtime,
            @RequestParam(required = false) String movieStatus,
            @RequestParam(required = false) String tagline,
            @RequestParam(required = false) BigDecimal voteAverage,
            @RequestParam(required = false) Integer voteCount,
            Model model) {

        try {

            if (popularity != null && popularity.compareTo(new BigDecimal("999999.999999")) > 0) {
                model.addAttribute("error", "La popularidad no puede superar los 999999.999999");
                return "movieCreate";
            }

            if (voteAverage != null && voteAverage.compareTo(new BigDecimal("10")) > 0) {
                model.addAttribute("error", "Debe der entre 0 i 10");
                return "movieCreate";
            }

            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setBudget(budget);
            movie.setHomepage(homepage);
            movie.setOverview(overview);
            movie.setPopularity(popularity);
            movie.setReleaseDate(releaseDate);
            movie.setRevenue(revenue);
            movie.setRuntime(runtime);
            movie.setMovieStatus(movieStatus);
            movie.setTagline(tagline);
            movie.setVoteAverage(voteAverage);
            movie.setVoteCount(voteCount);

            movieService.save(movie);

            model.addAttribute("success", "Creado con exito!");
            return "redirect:/movies/info?id=" + movie.getId();

        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "movieCreate";
        }
    }
}
