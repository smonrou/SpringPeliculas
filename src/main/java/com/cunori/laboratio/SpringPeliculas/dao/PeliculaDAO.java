package com.cunori.laboratio.SpringPeliculas.dao;

import com.cunori.laboratio.SpringPeliculas.service.Pelicula;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class PeliculaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE = "pelicula";
    private static final String COLUMNS = "titulo, director, anio_estreno, genero, duracion, calificacion";
    private static final String COLUMNS_SELECT = "id, " + COLUMNS;

    private static final RowMapper<Pelicula> rowMapper = new RowMapper<Pelicula>() {
        @Override
        public Pelicula mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pelicula p = new Pelicula();
            p.setId(rs.getInt("id"));
            p.setTitulo(rs.getString("titulo"));
            p.setDirector(rs.getString("director"));
            p.setAnioEstreno(rs.getObject("anio_estreno", Integer.class));
            p.setGenero(rs.getString("genero"));
            p.setDuracion(rs.getBigDecimal("duracion"));
            p.setCalificacion(rs.getObject("calificacion", Integer.class));
            return p;
        }
    };

    public List<Pelicula> findAll() {
        String sql = "SELECT " + COLUMNS_SELECT + " FROM " + TABLE;
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Pelicula> findAllPaginated(int offset, int limit) {
        String sql = "SELECT " + COLUMNS_SELECT + " FROM " + TABLE + " ORDER BY id LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, rowMapper, limit, offset);
    }

    public List<Pelicula> buscarPorTitulo(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return findAll();
        }
        try {
            String sql = "SELECT " + COLUMNS_SELECT + " FROM " + TABLE + " WHERE titulo LIKE ? ORDER BY id";
            String patron = "%" + nombre.trim() + "%";
            return jdbcTemplate.query(sql, rowMapper, patron);
        } catch (EmptyResultDataAccessException e) {
            return List.of();
        }
    }

    public long count() {
        String sql = "SELECT COUNT(*) FROM " + TABLE;
        try {
            Long total = jdbcTemplate.queryForObject(sql, Long.class);
            return total != null ? total : 0L;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public Optional<Pelicula> findById(Integer id) {
        String sql = "SELECT " + COLUMNS_SELECT + " FROM " + TABLE + " WHERE id = ?";
        try {
            List<Pelicula> list = jdbcTemplate.query(sql, rowMapper, id);
            return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Pelicula save(Pelicula pelicula) {
        String sql = "INSERT INTO " + TABLE + " (" + COLUMNS + ") VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pelicula.getTitulo());
            ps.setString(2, pelicula.getDirector());
            ps.setObject(3, pelicula.getAnioEstreno());
            ps.setString(4, pelicula.getGenero());
            ps.setBigDecimal(5, pelicula.getDuracion());
            ps.setObject(6, pelicula.getCalificacion());
            return ps;
        }, keyHolder);
        Number key = keyHolder.getKey();
        if (key != null) {
            pelicula.setId(key.intValue());
        }
        return pelicula;
    }

    public int update(Pelicula pelicula) {
        String sql = "UPDATE " + TABLE
                + " SET titulo = ?, director = ?, anio_estreno = ?, genero = ?, duracion = ?, calificacion = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                pelicula.getTitulo(),
                pelicula.getDirector(),
                pelicula.getAnioEstreno(),
                pelicula.getGenero(),
                pelicula.getDuracion(),
                pelicula.getCalificacion(),
                pelicula.getId());
    }

    public int deleteById(Integer id) {
        String sql = "DELETE FROM " + TABLE + " WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
