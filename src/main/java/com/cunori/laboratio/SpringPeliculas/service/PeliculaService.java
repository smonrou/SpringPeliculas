package com.cunori.laboratio.SpringPeliculas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cunori.laboratio.SpringPeliculas.dao.PeliculaDAO;
import com.cunori.laboratio.SpringPeliculas.model.Pelicula;
import java.util.List;


@Service
public class PeliculaService {
    @Autowired
    private PeliculaDAO peliculaDAO;

    public List<Pelicula> findAll() {
        return peliculaDAO.findAll();
    }

    public List<Pelicula> findAllPaginated(int offset, int limit) {
        return peliculaDAO.findAllPaginated(offset, limit);
    }

    public List<Pelicula> buscarPorTitulo(String nombre) {
        return peliculaDAO.buscarPorTitulo(nombre);
    }

    public long count() {
        return peliculaDAO.count();
    }

    public Pelicula findById(Integer id) {
        return peliculaDAO.findById(id).orElseThrow(() -> new RuntimeException("No se encontró la pelicula con el id: " + id));
    }

    public Boolean save(Pelicula pelicula) {
        if (pelicula.getTitulo() == null || pelicula.getTitulo().isBlank()) {
            throw new RuntimeException("El titulo de la pelicula es requerido");
        }
        if (pelicula.getDirector() == null || pelicula.getDirector().isBlank()) {
            throw new RuntimeException("El director de la pelicula es requerido");
        }
        if (pelicula.getAnioEstreno() == null) {
            throw new RuntimeException("El año de estreno de la pelicula es requerido");
        }
        if (pelicula.getGenero() == null || pelicula.getGenero().isBlank()) {
            throw new RuntimeException("El genero de la pelicula es requerido");
        }
        if (pelicula.getDuracion() == null) {
            throw new RuntimeException("La duracion de la pelicula es requerida");
        }
        if (pelicula.getCalificacion() == null) {
            throw new RuntimeException("La calificacion de la pelicula es requerida");
        }
        return peliculaDAO.save(pelicula);
    }

    public Boolean update(Pelicula pelicula) {
        if (pelicula.getId() == null) {
            throw new RuntimeException("El id de la pelicula es requerido");
        }
        return peliculaDAO.update(pelicula);
    }

    public void deleteById(Integer id) {
        peliculaDAO.deleteById(id);
    }
}

