package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.LanguageRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRoleRepo extends JpaRepository<LanguageRole, Integer> {
}
