package com.cunori.laboratio.SpringPeliculas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cunori.laboratio.SpringPeliculas.model.Pelicula;
import com.cunori.laboratio.SpringPeliculas.service.PeliculaService;

@Controller
public class CatalogoController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping({ "/", "/catalogo" })
    public String catalogo(Model model) {
        List<Pelicula> peliculas = peliculaService.findAll();
        model.addAttribute("peliculas", peliculas);
        return "index";
    }
}
