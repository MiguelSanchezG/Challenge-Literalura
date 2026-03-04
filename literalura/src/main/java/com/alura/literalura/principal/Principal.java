package com.alura.literalura.principal;

//import com.alura.literalura.model.Book;
import com.alura.literalura.dto.BookDTO;
import com.alura.literalura.dto.InfoDTO;
import com.alura.literalura.model.Book;
import com.alura.literalura.model.DatosInfo;
//import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.repository.BookRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private BookRepository repository;
    private List<Book> libros;

    public Principal(BookRepository repository) {
        this.repository = repository;
    }

    public void muestraElMenu(){
        var opcion = -1;
        while(opcion !=0){
            var menu = """
                    ----------------------
                    Por favor selecciona una opcion:
                    1- buscar libro por titulo
                    2- listar libros registrados
                    3- listar autores registrados
                    4- listar autores vivos en un determinado año
                    5- listar libros por idioma
                    0- salir                    
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1:
                    buscarTituloLibro();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando app...");
                    break;
                default:
                    System.out.println("Opcion invalida");

            }


        }
    }
    private void buscarTituloLibro() {
        BookDTO bookDTO = getDatosLibro();
        if(bookDTO==null){
            System.out.println("No existe ese libro");
        }else{
            Book libro = new Book(bookDTO);
            repository.save(libro);
            libro.imprimir();
        }

    }

    private void listarLibrosRegistrados() {
        libros = repository.findAll();

        libros.stream()
                .forEach(l-> l.imprimir());
    }

    private void listarAutoresRegistrados(){

    }

    private void listarAutoresVivos(){

    }

    private void listarLibrosIdioma(){

    }

    private BookDTO getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+nombreLibro.trim());
        if(json == null){
            return null;
        }
        BookDTO libro = conversor.obtenerPrimerResultado(json, BookDTO.class);
        System.out.println(libro);
        return libro;
    }

}
