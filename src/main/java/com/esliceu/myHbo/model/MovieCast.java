package com.esliceu.myHbo.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "movie_cast")
public class MovieCast {

    @EmbeddedId
    private MovieCastId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @Column(name = "character_name")
    private String characterName;

    @Column(name = "cast_order")
    private Integer castOrder;

    protected MovieCast() {

    }

    public MovieCast(Movie movie, Person person, Gender gender,
                     String characterName, Integer castOrder) {
        this.movie = movie;
        this.person = person;
        this.gender = gender;
        this.characterName = characterName;
        this.castOrder = castOrder;
        this.id = new MovieCastId(movie.getId(), person.getId());
    }

    public MovieCastId getId() {
        return id;
    }

    public void setId(MovieCastId id) {
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Integer getCastOrder() {
        return castOrder;
    }

    public void setCastOrder(Integer castOrder) {
        this.castOrder = castOrder;
    }
}
