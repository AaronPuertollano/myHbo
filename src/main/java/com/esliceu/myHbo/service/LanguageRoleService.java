package com.esliceu.myHbo.service;

import com.esliceu.myHbo.model.Language;
import com.esliceu.myHbo.model.LanguageRole;
import com.esliceu.myHbo.repo.LanguageRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageRoleService {

    @Autowired
    LanguageRoleRepo languageRoleRepo;

    public List<LanguageRole> findAll() {
        return languageRoleRepo.findAll();
    }

    public Optional<LanguageRole> findById(Integer id) {
        return languageRoleRepo.findById(id);
    }

    public LanguageRole save(LanguageRole languageRole) {
        return languageRoleRepo.save(languageRole);
    }

    public void delete(Integer id) {
        languageRoleRepo.deleteById(id);
    }

    public Integer getNextId() {
        Integer maxId = languageRoleRepo.findMaxId();
        return (maxId == null) ? 1 : maxId + 1;
    }
}
