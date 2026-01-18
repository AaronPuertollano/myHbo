package com.esliceu.myHbo.service;

import com.esliceu.myHbo.model.Country;
import com.esliceu.myHbo.model.Department;
import com.esliceu.myHbo.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    public Optional<Department> findById(Integer id) {
        return departmentRepo.findById(id);
    }

    public Department save(Department department) {
        return departmentRepo.save(department);
    }

    public void delete(Integer id) {
        departmentRepo.deleteById(id);
    }
}
