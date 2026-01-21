package com.esliceu.myHbo.controller;

import com.esliceu.myHbo.model.Country;
import com.esliceu.myHbo.model.Language;
import com.esliceu.myHbo.model.LanguageRole;
import com.esliceu.myHbo.service.LanguageRoleService;
import com.esliceu.myHbo.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LanguageController {

    @Autowired
    LanguageService languageService;

    @GetMapping("/language")
    public String getLanguage(Model model) {

        List<Language> language = languageService.findAll();
        model.addAttribute("language", language);
        return "language";
    }

    @PostMapping("/language")
    public String postLanguage(@RequestParam String languageName,
                              @RequestParam String languageCode) {

        Language language = new Language();
        language.setName(languageName);
        language.setCode(languageCode);

        languageService.save(language);

        return "redirect:/language";
    }

    @PostMapping("/language/delete")
    public String deleteLanguage(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        try {
            languageService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Idioma borrado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "No se puede borrar este idioma porque está siendo usado en películas");
        }
        return "redirect:/language";
    }

    @Autowired
    LanguageRoleService languageRoleService;

    @GetMapping("/languageRole")
    public String getLanguageRole(Model model) {
        List<LanguageRole> languageRoles = languageRoleService.findAll();
        model.addAttribute("languageRoles", languageRoles);
        return "languageRole";
    }

    @PostMapping("/languageRole")
    public String postLanguageRole( @RequestParam String languageR, RedirectAttributes redirectAttributes) {

        LanguageRole languageRole = new LanguageRole();
        languageRole.setId(languageRoleService.getNextId());
        languageRole.setLanguageRole(languageR);

        languageRoleService.save(languageRole);

        redirectAttributes.addFlashAttribute("success",
                "Género añadido correctamente con ID: " + languageRole.getId());

        return "redirect:/languageRole";
    }

    @PostMapping("/languageRole/delete")
    public String deleteLanguageRole(@RequestParam Integer id,
                                     RedirectAttributes redirectAttributes) {
        try {
            languageRoleService.delete(id);
            redirectAttributes.addFlashAttribute("success",
                    "Rol de idioma borrado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "No se puede borrar este rol porque está siendo usado en películas");
        }
        return "redirect:/languageRole";
    }
}
