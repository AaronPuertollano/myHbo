package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.MovieCast;
import com.esliceu.myHbo.model.MovieCastId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCastRepo extends JpaRepository<MovieCast, MovieCastId> {
}
