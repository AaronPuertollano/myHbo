package com.esliceu.myHbo.controller;

import com.esliceu.myHbo.model.Country;
import com.esliceu.myHbo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("/country")
    public String getCountry(Model model) {

        List<Country> country = countryService.findAll();
        model.addAttribute("country", country);
        return "country";
    }

    @PostMapping("/country")
    public String postCountry(@RequestParam String countryName,
                              @RequestParam String isoCode) {

        Country country = new Country();
        country.setCountryName(countryName);
        country.setIsoCode(isoCode);

        countryService.save(country);

        return "redirect:/country";
    }

    @PostMapping("/country/delete")
    public String deleteCountry(@RequestParam Integer id, Model model) {
        try {
            countryService.delete(id);
            model.addAttribute("success", "País eliminado correctamente");
        } catch (Exception e) {
            model.addAttribute("error", "No se puede eliminar este país porque está siendo utilizado en otras películas");
        }
        model.addAttribute("country", countryService.findAll());
        return "country";
    }

    @PostMapping("/country/update")
    public String updateCountry(@RequestParam Integer id,
                                @RequestParam String countryName,
                                @RequestParam String isoCode) {

        countryService.update(id, countryName, isoCode);
        return "redirect:/country";
    }
}
