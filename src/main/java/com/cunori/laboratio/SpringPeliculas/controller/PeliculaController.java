package com.cunori.laboratio.SpringPeliculas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.cunori.laboratio.SpringPeliculas.service.PeliculaService;
import com.cunori.laboratio.SpringPeliculas.model.Pelicula;
import java.util.*;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "*")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<List<Pelicula>> findPaginated(@RequestParam int page, @RequestParam int size) {
        List<Pelicula> content = peliculaService.findAllPaginated(page, size);
        return ResponseEntity.ok(content);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Pelicula>> findByTitle(@RequestParam(required = false) String nombre) {
        List<Pelicula> lista = peliculaService.findByTitle(nombre != null ? nombre : "");
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> count() {
        long total = peliculaService.count();
        return ResponseEntity.ok(Map.of("count", total));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> findById(@PathVariable Integer id) {
        try {
            Pelicula pelicula = peliculaService.findById(id);
            return ResponseEntity.ok(pelicula);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Pelicula pelicula) {
        Boolean creada = peliculaService.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> update(@PathVariable Integer id, @RequestBody Pelicula pelicula) {
        try {
            pelicula.setId(id);
            peliculaService.update(pelicula);
            Pelicula actualizada = peliculaService.findById(id);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            peliculaService.findById(id);
            peliculaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
