package com.alura.literalura.repository;

import com.alura.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("""
       SELECT b FROM Book b
       JOIN b.languages l
       WHERE l = :idioma
       """)
    List<Book> findAutorPorIdioma(@Param("idioma") String idioma);


}
