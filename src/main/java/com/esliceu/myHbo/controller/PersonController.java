package com.esliceu.myHbo.controller;

import com.esliceu.myHbo.model.Keyword;
import com.esliceu.myHbo.model.Person;
import com.esliceu.myHbo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/person")
    public String getKPerson(Model model) {
        List<Person> person = personService.findAll();
        model.addAttribute("person", person);
        return "person";
    }

    @PostMapping("/person")
    public String postKeyword(@RequestParam String personName,
                              RedirectAttributes redirectAttributes) {
        try {
            Person person = new Person();
            person.setId(personService.getNextId());
            person.setPersonName(personName);

            personService.save(person);

            redirectAttributes.addFlashAttribute("success",
                    "Persona añadida correctamente con ID: " + person.getId());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Error al añadir la persona: " + e.getMessage());
        }
        return "redirect:/person";
    }

    @PostMapping("/person/delete")
    public String deletePerson(@RequestParam Integer id,
                                RedirectAttributes redirectAttributes) {
        try {
            if (personService.findById(id).isPresent()) {
                personService.delete(id);
                redirectAttributes.addFlashAttribute("success",
                        "Persona borrada correctamente");
            } else {
                redirectAttributes.addFlashAttribute("error",
                        "Persona no encontrada");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "No se puede borrar esta perona porque está siendo usado otra tabla");
        }
        return "redirect:/person";
    }

    @GetMapping("/person/search")
    @ResponseBody
    public List<Person> search(@RequestParam String term){
        List<Person> persons = personService.findByName(term);
        return persons;
    }

    @GetMapping("/person/{id}")
    @ResponseBody
    public Optional<Person> search(@PathVariable int id){
        return personService.findById(id);
    }

    @PostMapping("/person/update")
    public String updatePerson(@RequestParam Integer id,
                                @RequestParam String personName) {

        personService.update(id, personName);
        return "redirect:/person";
    }

}
