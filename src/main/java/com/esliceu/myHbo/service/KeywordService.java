package com.esliceu.myHbo.service;

import com.esliceu.myHbo.model.Country;
import com.esliceu.myHbo.model.Keyword;
import com.esliceu.myHbo.repo.KeywordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KeywordService {

    @Autowired
    KeywordRepo keywordRepo;

    public List<Keyword> findAll() {
        return keywordRepo.findAll();
    }

    public Optional<Keyword> findById(Integer id) {
        return keywordRepo.findById(id);
    }

    public Keyword save(Keyword keyword) {
        return keywordRepo.save(keyword);
    }

    public void delete(Integer id) {
        keywordRepo.deleteById(id);
    }

    public Integer getNextId() {
        return keywordRepo.getNextId();
    }
}
