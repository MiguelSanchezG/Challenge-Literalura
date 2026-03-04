package com.alura.literalura.model;


import com.alura.literalura.dto.AuthorDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private Integer birth_year;
    private Integer death_year;
    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Author(){}

    public Author(AuthorDTO authorDTO){
        this.name = authorDTO.name();
        this.birth_year = authorDTO.birth_year();
        this.death_year = authorDTO.death_year();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", birth_year=" + birth_year +
                ", death_year=" + death_year;
    }

    public void imprimir(){
        System.out.println("Autor: "+name);
        System.out.println("Fecha nacimiento: "+birth_year);
        System.out.println("Fecha de fallecimiento: "+death_year);
        System.out.print("[");
        imprimirLibros();
        System.out.print("]");
        System.out.println();
    }

    public void imprimirLibros(){
        if(!books.isEmpty()){
            for(Book book: books){
                System.out.print(" "+book.getTitle());
            }
        }
        else{
            System.out.println();
        }
    }
}
