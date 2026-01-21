package com.esliceu.myHbo.controller;

import com.esliceu.myHbo.model.Country;
import com.esliceu.myHbo.model.Department;
import com.esliceu.myHbo.model.Keyword;
import com.esliceu.myHbo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/department")
    public String getDepartment(Model model) {

        List<Department> department = departmentService.findAll();
        model.addAttribute("department", department);
        return "department";
    }

    @PostMapping("/department")
    public String postDepartment(@RequestParam String departmentName, RedirectAttributes redirectAttributes) {

        try {
            Department department = new Department();
            department.setDepartmentName(departmentName);
            departmentService.save(department);

            redirectAttributes.addFlashAttribute("success",
                    "Depratemento añadido correctamente con ID: " + department.getId());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Error al añadir el depratemento: " + e.getMessage());
        }

        return "redirect:/department";
    }

    @PostMapping("/department/delete")
    public String deleteDepartment(@RequestParam Integer id,
                                RedirectAttributes redirectAttributes) {
        try {
            if (departmentService.findById(id).isPresent()) {
                departmentService.delete(id);
                redirectAttributes.addFlashAttribute("success",
                        "Departamento borrado correctamente");
            } else {
                redirectAttributes.addFlashAttribute("error",
                        "Departamento no encontrado");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "No se puede borrar este Departamento porque está siendo usado en películas");
        }
        return "redirect:/department";
    }
}
