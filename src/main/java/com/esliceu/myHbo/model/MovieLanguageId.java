package com.esliceu.myHbo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieLanguageId implements Serializable {

    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "language_id")
    private Integer languageId;

    public MovieLanguageId() {
    }

    public MovieLanguageId(Integer movieId, Integer languageId) {
        this.movieId = movieId;
        this.languageId = languageId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieLanguageId)) return false;
        MovieLanguageId that = (MovieLanguageId) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(languageId, that.languageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, languageId);
    }
}
