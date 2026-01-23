package com.esliceu.myHbo.controller;
import com.esliceu.myHbo.model.Keyword;
import com.esliceu.myHbo.model.ProductionCompany;
import com.esliceu.myHbo.service.ProductionCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductionCompanyController {

    @Autowired
    ProductionCompanyService productionCompanyService;

    @GetMapping("/productionCompany")
    public String getProductionCompany(Model model) {
        List<ProductionCompany> productionCompany = productionCompanyService.findAll();
        model.addAttribute("productionCompany", productionCompany);
        return "productionCompany";
    }

    @PostMapping("/productionCompany")
    public String postKeyword(@RequestParam String companyName,
                              RedirectAttributes redirectAttributes) {
        try {
            ProductionCompany productionCompany = new ProductionCompany();
            productionCompany.setId(productionCompanyService.getNextId());
            productionCompany.setCompanyName(companyName);

            productionCompanyService.save(productionCompany);

            redirectAttributes.addFlashAttribute("success",
                    "Empresa añadido correctamente con ID: " + productionCompany.getId());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Error al añadir la empresa: " + e.getMessage());
        }
        return "redirect:/productionCompany";
    }

    @PostMapping("/productionCompany/delete")
    public String deleteProductionCompany(@RequestParam Integer id,
                                RedirectAttributes redirectAttributes) {
        try {
            if (productionCompanyService.findById(id).isPresent()) {
                productionCompanyService.delete(id);
                redirectAttributes.addFlashAttribute("success",
                        "Empresa borrado correctamente");
            } else {
                redirectAttributes.addFlashAttribute("error",
                        "Empresa no encontrada");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "No se puede borrar esta empresa porque está siendo usado en otra tabla");
        }
        return "redirect:/productionCompany";
    }

    @PostMapping("/productionCompany/update")
    public String updateProductionCompany(@RequestParam Integer id,
                               @RequestParam String companyName) {

        productionCompanyService.update(id, companyName);
        return "redirect:/productionCompany";
    }
}
