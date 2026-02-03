package com.esliceu.myHbo.service;

import com.esliceu.myHbo.model.*;
import com.esliceu.myHbo.repo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieService {

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    GenreRepo genreRepo;

    @Autowired
    CountryRepo countryRepo;

    @Autowired
    ProductionCompanyRepo productionCompanyRepo;

    @Autowired
    KeywordRepo keywordRepo;

    public List<Movie> findAll() {
        return movieRepo.findAll();
    }

    public Optional<Movie> findById(Integer id) {
        return movieRepo.findById(id);
    }

    public Movie save(Movie movie) {
        return movieRepo.save(movie);
    }

    @Transactional
    public void delete(Integer id) {

        if (!movieRepo.existsById(id)) {
            throw new RuntimeException("Movie not found");
        }
        
        movieRepo.deleteCastByMovieId(id);
        movieRepo.deleteCrewByMovieId(id);
        movieRepo.deleteLanguagesByMovieId(id);
        movieRepo.deleteGenresByMovieId(id);
        movieRepo.deleteCountriesByMovieId(id);
        movieRepo.deleteCompaniesByMovieId(id);
        movieRepo.deleteKeywordsByMovieId(id);

        movieRepo.deleteById(id);
    }

    public List<String> getAutocompleteTitles(String term) {
        return movieRepo.findTitlesByTerm(term);
    }

    public List<Movie> searchMovies(String searchType, String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return movieRepo.findAll();
        }

        return switch (searchType) {
            case "title" -> movieRepo.findByTitleContaining(searchTerm);
            case "actor" -> movieRepo.findByActorName(searchTerm);
            case "character" -> movieRepo.findByCharacterName(searchTerm);
            case "genre" -> movieRepo.findByGenreName(searchTerm);
            case "director" -> movieRepo.findByDirectorName(searchTerm);
            default -> movieRepo.findAll();
        };
    }

    @Transactional
    public Optional<Movie> findByIdWithAllInfo(Integer id) {
        return movieRepo.findByIdWithAllRelations(id);
    }

    @Transactional
    public void updateMovieRelations(Movie movie,
                                     List<Integer> genreIds,
                                     List<Integer> countryIds,
                                     List<Integer> companyIds,
                                     List<Integer> keywordIds) {

        if (genreIds != null) {
            Set<Genre> genres = new HashSet<>(genreRepo.findAllById(genreIds));
            movie.setGenres(genres);
        } else {
            movie.setGenres(new HashSet<>());
        }

        if (countryIds != null) {
            Set<Country> countries = new HashSet<>(countryRepo.findAllById(countryIds));
            movie.setCountries(countries);
        } else {
            movie.setCountries(new HashSet<>());
        }

        if (companyIds != null) {
            Set<ProductionCompany> companies = new HashSet<>(productionCompanyRepo.findAllById(companyIds));
            movie.setProductionCompanies(companies);
        } else {
            movie.setProductionCompanies(new HashSet<>());
        }

        if (keywordIds != null) {
            Set<Keyword> keywords = new HashSet<>(keywordRepo.findAllById(keywordIds));
            movie.setKeywords(keywords);
        } else {
            movie.setKeywords(new HashSet<>());
        }
    }

}
