package com.alura.literalura.repository;

import com.alura.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    @Query("SELECT DISTINCT a FROM Author a JOIN FETCH a.books")
    Set<Author> findAutorYLibros();

    @Query("SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.books WHERE a.birth_year <= :anio AND (a.death_year IS NULL OR a.death_year >= :anio)")
    Set<Author> findAutoresVivosSegunAnio(@Param("anio") Integer anio);

    Optional<Author> findByName(String name);
}
