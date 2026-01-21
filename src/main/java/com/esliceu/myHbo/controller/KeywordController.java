package com.esliceu.myHbo.controller;

import com.esliceu.myHbo.model.Genre;
import com.esliceu.myHbo.model.Keyword;
import com.esliceu.myHbo.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class KeywordController {

    @Autowired
    KeywordService keywordService;

    @GetMapping("/keyword")
    public String getKeyword(Model model) {
        List<Keyword> keywords = keywordService.findAll();
        model.addAttribute("keywords", keywords);
        return "keyword";
    }

    @PostMapping("/keyword")
    public String postKeyword(@RequestParam String keywordName,
                              RedirectAttributes redirectAttributes) {
        try {
            Keyword keyword = new Keyword();
            keyword.setId(keywordService.getNextId());
            keyword.setKeywordName(keywordName);

            keywordService.save(keyword);

            redirectAttributes.addFlashAttribute("success",
                    "Keyword añadido correctamente con ID: " + keyword.getId());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Error al añadir keyword: " + e.getMessage());
        }
        return "redirect:/keyword";
    }

    @PostMapping("/keyword/delete")
    public String deleteKeyword(@RequestParam Integer id,
                                RedirectAttributes redirectAttributes) {
        try {
            if (keywordService.findById(id).isPresent()) {
                keywordService.delete(id);
                redirectAttributes.addFlashAttribute("success",
                        "Keyword borrado correctamente");
            } else {
                redirectAttributes.addFlashAttribute("error",
                        "Keyword no encontrado");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "No se puede borrar este keyword porque está siendo usado en películas");
        }
        return "redirect:/keyword";
    }

}
