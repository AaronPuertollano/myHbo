package com.esliceu.myHbo.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "movie_crew")
public class MovieCrew {

    @EmbeddedId
    private MovieCrewId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "job")
    private String job;

    protected MovieCrew() {
        // required by JPA
    }

    public MovieCrew(Movie movie, Person person, Department department, String job) {
        this.movie = movie;
        this.person = person;
        this.department = department;
        this.job = job;
        this.id = new MovieCrewId(movie.getId(), person.getId());
    }

    public MovieCrewId getId() {
        return id;
    }

    public void setId(MovieCrewId id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
