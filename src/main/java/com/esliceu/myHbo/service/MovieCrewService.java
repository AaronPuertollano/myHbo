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
    private MovieRepo movieRepository;

    @Autowired
    private PersonRepo personRepository;

    @Autowired
    private DepartmentRepo departmentRepository;

    @Autowired
    private MovieCrewRepo movieCrewRepository;

    @Transactional
    public void addCrewMember(Integer movieId, Integer personId, Integer departmentId, String job) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        MovieCrew movieCrew = new MovieCrew(movie, person, department, job);
        movie.addCrew(movieCrew);
        movieRepository.save(movie);
    }

    @Transactional
    public void deleteCrewMember(Integer movieId, Integer personId) {
        MovieCrewId id = new MovieCrewId(movieId, personId);
        movieCrewRepository.deleteById(id);
    }
}
