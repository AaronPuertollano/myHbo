package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepo extends JpaRepository <Keyword, Integer> {
}
