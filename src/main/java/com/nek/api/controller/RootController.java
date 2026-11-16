package com.nek.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Hidden;

/** La raíz lleva directo a la documentación interactiva. */
@Controller
@Hidden
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:/docs";
    }
}
