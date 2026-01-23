package com.esliceu.myHbo.service;

import com.esliceu.myHbo.model.Country;
import com.esliceu.myHbo.model.Keyword;
import com.esliceu.myHbo.model.Language;
import com.esliceu.myHbo.repo.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepo languageRepo;

    public List<Language> findAll() {
        return languageRepo.findAll();
    }

    public Optional<Language> findById(Integer id) {
        return languageRepo.findById(id);
    }

    public Language save(Language language) {
        return languageRepo.save(language);
    }

    public void delete(Integer id) {
        languageRepo.deleteById(id);
    }

    public Language update(Integer id, String languageCode, String languageName) {

        Language language = languageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        language.setCode(languageCode);
        language.setName(languageName);

        return languageRepo.save(language);
    }
}
