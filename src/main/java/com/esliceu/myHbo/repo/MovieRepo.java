package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.Genre;
import com.esliceu.myHbo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

    // Autocompletado
    @Query("SELECT m.title FROM Movie m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<String> findTitlesByTerm(@Param("term") String term);

    @Query("SELECT m FROM Movie m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Movie> findByTitleContaining(@Param("title") String title);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.cast mc JOIN mc.person p WHERE LOWER(p.personName) LIKE LOWER(CONCAT('%', :actorName, '%'))")
    List<Movie> findByActorName(@Param("actorName") String actorName);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.cast mc WHERE LOWER(mc.characterName) LIKE LOWER(CONCAT('%', :characterName, '%'))")
    List<Movie> findByCharacterName(@Param("characterName") String characterName);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.genres g WHERE LOWER(g.genreName) LIKE LOWER(CONCAT('%', :genreName, '%'))")
    List<Movie> findByGenreName(@Param("genreName") String genreName);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.crew mc JOIN mc.person p WHERE LOWER(p.personName) LIKE LOWER(CONCAT('%', :directorName, '%')) AND LOWER(mc.job) = 'director'")
    List<Movie> findByDirectorName(@Param("directorName") String directorName);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.productionCompanies pc WHERE pc.id = :companyId")
    Set<Movie> findByProductionCompanyId(@Param("companyId") Integer companyId);

    @Query("SELECT DISTINCT m FROM Movie m " +
            "LEFT JOIN FETCH m.cast " +
            "LEFT JOIN FETCH m.crew " +
            "LEFT JOIN FETCH m.genres " +
            "LEFT JOIN FETCH m.productionCompanies " +
            "LEFT JOIN FETCH m.countries " +
            "LEFT JOIN FETCH m.keywords " +
            "LEFT JOIN FETCH m.movieLanguages " +
            "WHERE m.id = :id")
    Optional<Movie> findByIdWithAllRelations(@Param("id") Integer id);
}
