package com.esliceu.myHbo.controller;

import com.esliceu.myHbo.model.Gender;
import com.esliceu.myHbo.model.Genre;
import com.esliceu.myHbo.model.Language;
import com.esliceu.myHbo.service.GenderService;
import com.esliceu.myHbo.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping("/genre")
    public String getGenre(Model model) {

        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        return "genre";
    }

    @PostMapping("/genre")
    public String postGenre(@RequestParam String genreName,
                            RedirectAttributes redirectAttributes) {

        Genre genre = new Genre();

        Integer nextId = genreService.getNextId();
        genre.setId(nextId);
        genre.setGenreName(genreName);

        genreService.save(genre);

        redirectAttributes.addFlashAttribute("success",
                "Género añadido correctamente con ID: " + genre.getId());

        return "redirect:/genre";
    }

    @PostMapping("/genre/delete")
    public String deleteGenre(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        try {
            genreService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Genero borrado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "No se puede borrar este genero porque está siendo usado en películas");
        }
        return "redirect:/genre";
    }

    @Autowired
    GenderService genderService;

    @GetMapping("/gender")
    public String getGender(Model model) {

        List<Gender> genders = genderService.findAll();
        model.addAttribute("genders", genders);
        return "gender";
    }

    @PostMapping("/gender")
    public String postGender(@RequestParam Integer id,
                            @RequestParam String genderN,
                            RedirectAttributes redirectAttributes) {

        if (genderService.findById(id).isPresent()) {
            redirectAttributes.addFlashAttribute("error",
                    "El ID " + id + " ya está en uso. Por favor, elige otro.");
            return "redirect:/gender";
        }

        Gender gender = new Gender();
        gender.setId(id);
        gender.setGender(genderN);

        genderService.save(gender);
        redirectAttributes.addFlashAttribute("success",
                "Género añadido correctamente");

        return "redirect:/gender";
    }

    @PostMapping("/gender/delete")
    public String deleteGender(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        try {
            genderService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Genero borrado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "No se puede borrar este genero porque está siendo usado en películas");
        }
        return "redirect:/gender";
    }

}
