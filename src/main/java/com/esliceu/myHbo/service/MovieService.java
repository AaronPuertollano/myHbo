package com.esliceu.myHbo.service;

import com.esliceu.myHbo.model.Country;
import com.esliceu.myHbo.model.Movie;
import com.esliceu.myHbo.repo.MovieRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepo movieRepo;

    public List<Movie> findAll() {
        return movieRepo.findAll();
    }

    public Optional<Movie> findById(Integer id) {
        return movieRepo.findById(id);
    }

    public Movie save(Movie movie) {
        return movieRepo.save(movie);
    }

    public void delete(Integer id) {
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

}
