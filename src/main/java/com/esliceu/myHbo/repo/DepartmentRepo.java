package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository <Department, Integer> {
}
