package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepo extends JpaRepository <Keyword, Integer> {
    @Query(value = "SELECT COALESCE(MAX(keyword_id), 0) + 1 FROM keyword", nativeQuery = true)
    Integer getNextId();
}
