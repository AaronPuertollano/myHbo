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

    @PostMapping("/genre/update")
    public String updateGenre(@RequestParam Integer id,
                               @RequestParam String genreName) {

        genreService.update(id, genreName);
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
    public String postGender(@RequestParam String genderN,
                            RedirectAttributes redirectAttributes) {

        Gender gender = new Gender();
        gender.setId(genderService.getNextId());
        gender.setGender(genderN);

        genderService.save(gender);

        redirectAttributes.addFlashAttribute("success",
                "Género añadido correctamente con ID: " + gender.getId());

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

    @PostMapping("/gender/update")
    public String updateGender(@RequestParam Integer id,
                                   @RequestParam String genderName) {

        genderService.update(id, genderName);
        return "redirect:/gender";
    }

}
