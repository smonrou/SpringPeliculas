package com.cunori.laboratio.SpringPeliculas.service;

import java.math.BigDecimal;
import java.util.Objects;

public class Pelicula {

    private Integer id;
    private String titulo;
    private String director;
    private Integer anioEstreno;
    private String genero;
    private BigDecimal duracion;
    private Integer calificacion;

    public Pelicula() {
    }

    public Pelicula(Integer id, String titulo, String director, Integer anioEstreno, String genero, BigDecimal duracion,
            Integer calificacion) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.anioEstreno = anioEstreno;
        this.genero = genero;
        this.duracion = duracion;
        this.calificacion = calificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getAnioEstreno() {
        return anioEstreno;
    }

    public void setAnioEstreno(Integer anioEstreno) {
        this.anioEstreno = anioEstreno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public BigDecimal getDuracion() {
        return duracion;
    }

    public void setDuracion(BigDecimal duracion) {
        this.duracion = duracion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String toString() {
        return "Pelicula [id=" + id + ", titulo=" + titulo + ", director=" + director + ", anioEstreno=" + anioEstreno
                + ", genero=" + genero + ", duracion=" + duracion + ", calificacion=" + calificacion + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(id, pelicula.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}