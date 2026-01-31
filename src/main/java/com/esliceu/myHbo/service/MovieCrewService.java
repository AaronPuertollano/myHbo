package com.esliceu.myHbo.service;

import com.esliceu.myHbo.model.*;
import com.esliceu.myHbo.repo.DepartmentRepo;
import com.esliceu.myHbo.repo.MovieCrewRepo;
import com.esliceu.myHbo.repo.MovieRepo;
import com.esliceu.myHbo.repo.PersonRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MovieCrewService {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private MovieCrewRepo movieCrewRepo;

    @Transactional
    public void addCrewMember(Integer movieId, Integer personId, Integer departmentId, String job) {

        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        MovieCrew movieCrew = new MovieCrew(movie, person, department, job);
        movie.addCrew(movieCrew);
        movieRepo.save(movie);
    }

    @Transactional
    public void deleteCrewMember(Integer movieId, Integer personId) {
        MovieCrewId id = new MovieCrewId(movieId, personId);
        movieCrewRepo.deleteById(id);
    }
}
