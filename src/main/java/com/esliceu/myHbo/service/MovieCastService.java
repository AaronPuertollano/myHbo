package com.esliceu.myHbo.service;

import com.esliceu.myHbo.model.*;
import com.esliceu.myHbo.repo.GenderRepo;
import com.esliceu.myHbo.repo.MovieCastRepo;
import com.esliceu.myHbo.repo.MovieRepo;
import com.esliceu.myHbo.repo.PersonRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MovieCastService {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private GenderRepo genderRepo;

    @Autowired
    private MovieCastRepo movieCastRepo;

    @Transactional
    public void addCastMember(Integer movieId, Integer personId, Integer genderId,
                              String characterName, Integer castOrder) {

        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        Gender gender = genderRepo.findById(genderId)
                .orElseThrow(() -> new RuntimeException("Gender not found"));

        MovieCast movieCast = new MovieCast(movie, person, gender, characterName, castOrder);
        movie.addCast(movieCast);
        movieRepo.save(movie);
    }

    @Transactional
    public void deleteCastMember(Integer movieId, Integer personId) {
        MovieCastId id = new MovieCastId(movieId, personId);
        movieCastRepo.deleteById(id);
    }
}