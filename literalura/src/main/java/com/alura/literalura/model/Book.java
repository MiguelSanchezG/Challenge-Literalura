package com.alura.literalura.model;


import com.alura.literalura.dto.AuthorDTO;
import com.alura.literalura.dto.BookDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.joining;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String title;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> languages;
    private Double download_count;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "obras",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    public Book(){

    }

    public Book(BookDTO bookDTO){
        this.title = bookDTO.titulo();
        this.languages = bookDTO.idioma();
        this.download_count = bookDTO.numeroDescargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }

    public Double getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Double download_count) {
        this.download_count = download_count;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", languages=" + languages +
                ", download_count=" + download_count +
                ", authors=" + authors ;
    }


}
