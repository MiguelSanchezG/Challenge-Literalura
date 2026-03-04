package com.alura.literalura.model;


import com.alura.literalura.dto.AuthorDTO;
import com.alura.literalura.dto.BookDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
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
    private List<String> languages;
    private Double download_count;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "obras",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

    public Book(){

    }

    public Book(BookDTO bookDTO){
        this.title = bookDTO.titulo();
        this.languages = bookDTO.idioma();
        this.download_count = bookDTO.numeroDescargas();
        this.authors = bookDTO.authors()
                .stream()
                .map(Author::new)
                .toList();
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

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Double getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Double download_count) {
        this.download_count = download_count;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
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


    public void imprimir(){
        System.out.println("----- LIBRO -----");
        System.out.println(" Titulo: "+title);
        System.out.print(" Autor:");
        imprimirAutores();
        System.out.println();
        System.out.println(" Idioma: "+String.join(", ", languages));
        System.out.println(" Numero de descargas: "+download_count);
        System.out.println();

    }

    public void imprimirAutores(){
        if(!authors.isEmpty()){
            for(Author author: authors){
                System.out.print(" "+author.getName());
            }
        }
        else{
            System.out.println();
        }
    }

}
