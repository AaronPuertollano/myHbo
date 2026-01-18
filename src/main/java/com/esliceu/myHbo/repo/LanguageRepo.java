package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepo extends JpaRepository<Language, Integer> {
}
