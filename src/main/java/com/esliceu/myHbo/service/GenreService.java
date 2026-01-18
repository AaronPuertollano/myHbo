package com.esliceu.myHbo.service;

import com.esliceu.myHbo.model.Country;
import com.esliceu.myHbo.model.Genre;
import com.esliceu.myHbo.repo.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    GenreRepo genreRepo;

    public List<Genre> findAll() {
        return genreRepo.findAll();
    }

    public Optional<Genre> findById(Integer id) {
        return genreRepo.findById(id);
    }

    public Genre save(Genre genre) {
        return genreRepo.save(genre);
    }

    public void delete(Integer id) {
        genreRepo.deleteById(id);
    }
}
