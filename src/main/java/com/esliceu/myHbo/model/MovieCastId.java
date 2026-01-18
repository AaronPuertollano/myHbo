package com.esliceu.myHbo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieCastId implements Serializable {

    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "person_id")
    private Integer personId;

    public MovieCastId() {
    }

    public MovieCastId(Integer movieId, Integer personId) {
        this.movieId = movieId;
        this.personId = personId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieCastId)) return false;
        MovieCastId that = (MovieCastId) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, personId);
    }
}
