package com.esliceu.myHbo.model;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "movie_languages")
public class MovieLanguage {

    @EmbeddedId
    private MovieLanguageId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("languageId")
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_role_id", nullable = false)
    private LanguageRole languageRole;

    public MovieLanguage() {
    }

    public MovieLanguage(Movie movie, Language language, LanguageRole languageRole) {
        this.movie = movie;
        this.language = language;
        this.languageRole = languageRole;
        this.id = new MovieLanguageId(movie.getId(), language.getId());
    }

    public MovieLanguageId getId() {
        return id;
    }

    public void setId(MovieLanguageId id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public LanguageRole getLanguageRole() {
        return languageRole;
    }

    public void setLanguageRole(LanguageRole languageRole) {
        this.languageRole = languageRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieLanguage)) return false;
        MovieLanguage that = (MovieLanguage) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
