package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.Genre;
import com.esliceu.myHbo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {
}
