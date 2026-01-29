package com.esliceu.myHbo.controller;

import com.esliceu.myHbo.model.*;
import com.esliceu.myHbo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class MovieEditController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ProductionCompanyService companyService;

    @Autowired
    private KeywordService keywordService;

    @GetMapping("/movies/edit")
    public String editMovieForm(@RequestParam Integer id, Model model) {

        Optional<Movie> movieOpt = movieService.findByIdWithAllInfo(id);

        if (movieOpt.isEmpty()) {
            model.addAttribute("error", "Pel·lícula no trobada");
            return "redirect:/movies/search";
        }

        Movie movie = movieOpt.get();

        model.addAttribute("movie", movie);

        model.addAttribute("allGenres", genreService.findAll());
        model.addAttribute("allCountries", countryService.findAll());
        model.addAttribute("allCompanies", companyService.findAll());
        model.addAttribute("allKeywords", keywordService.findAll());

        model.addAttribute("selectedGenreIds",
                movie.getGenres().stream().map(Genre::getId).toList());
        model.addAttribute("selectedCountryIds",
                movie.getCountries().stream().map(Country::getId).toList());
        model.addAttribute("selectedCompanyIds",
                movie.getProductionCompanies().stream().map(ProductionCompany::getId).toList());
        model.addAttribute("selectedKeywordIds",
                movie.getKeywords().stream().map(Keyword::getId).toList());

        return "movieEdit";
    }

    @PostMapping("/movies/edit")
    public String updateMovie(
            @RequestParam Integer id,
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
            @RequestParam(required = false) List<Integer> genreIds,
            @RequestParam(required = false) List<Integer> countryIds,
            @RequestParam(required = false) List<Integer> companyIds,
            @RequestParam(required = false) List<Integer> keywordIds,
            Model model) {

        try {
            Optional<Movie> movieOpt = movieService.findById(id);

            if (movieOpt.isEmpty()) {
                model.addAttribute("error", "Pel·lícula no trobada");
                return "redirect:/movies/search";
            }

            Movie movie = movieOpt.get();

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

            movieService.updateMovieRelations(movie, genreIds, countryIds, companyIds, keywordIds);

            movieService.save(movie);

            return "redirect:/movies/info?id=" + id;

        } catch (Exception e) {
            model.addAttribute("error", "Error al actualitzar: " + e.getMessage());
            return "redirect:/movies/edit?id=" + id;
        }
    }

    @GetMapping("/movies/edit/crew")
    public String editMovieCrew(@RequestParam Integer id, Model model){

        Optional<Movie> movieOpt = movieService.findByIdWithAllInfo(id);

        if (movieOpt.isEmpty()) {
            model.addAttribute("error", "Pel·lícula no trobada");
            return "redirect:/movies/search";
        }

        Movie movie = movieOpt.get();
        model.addAttribute("crew", movie.getCrew());

        return "movieEditCrew";
    }

    @GetMapping("/movies/edit/cast")
    public String editMovieCast(@RequestParam Integer id, Model model){

        Optional<Movie> movieOpt = movieService.findByIdWithAllInfo(id);

        if (movieOpt.isEmpty()) {
            model.addAttribute("error", "Pel·lícula no trobada");
            return "redirect:/movies/search";
        }

        Movie movie = movieOpt.get();
        model.addAttribute("cast", movie.getCast());

        return "movieEditCast";
    }


}
