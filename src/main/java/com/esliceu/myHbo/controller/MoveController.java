package com.esliceu.myHbo.controller;

import com.esliceu.myHbo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MoveController {

    @GetMapping("/home")
    public String getHome(Model model) {
        return "home";
    }

    @GetMapping("/crud")
    public String getCrud(Model model) {
        return "crud";
    }

    @GetMapping("/moviehome")
    public String getMovieHome(Model model) {
        return "moviehome";
    }
}
