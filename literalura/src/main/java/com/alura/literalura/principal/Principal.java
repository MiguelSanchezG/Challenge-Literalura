package com.alura.literalura.principal;

//import com.alura.literalura.model.Book;
import com.alura.literalura.dto.AuthorDTO;
import com.alura.literalura.dto.BookDTO;
import com.alura.literalura.model.Author;
import com.alura.literalura.model.Book;
//import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.repository.AuthorRepository;
import com.alura.literalura.repository.BookRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private BookRepository repository;
    private AuthorRepository authorRepository;
    private Set<Book> libros;
    private Set<Author> autores;

    public Principal(BookRepository repository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
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
        try{
            BookDTO bookDTO = getDatosLibro();
            Optional<Book> libroExistente = repository.findByTitle(bookDTO.titulo());
            if(libroExistente.isPresent()){
                System.out.println("Libro ya existe en la base de datos");
                return;
            }
            Book libro = new Book(bookDTO);
            autores = new HashSet<>();
            Author autorCrear;
            for(AuthorDTO authorDTO: bookDTO.authors()){
                Optional<Author> autor = authorRepository.findByName(authorDTO.name());
                if(autor.isPresent()){
                    autorCrear = autor.get();
                }
                else{
                    autorCrear = authorRepository.save(new Author(authorDTO));
                }
                autores.add(autorCrear);
            }

            libro.setAuthors(autores);
            repository.save(libro);
            System.out.println("----- LIBRO -----");
            System.out.println(" Titulo: "+libro.getTitle());
            System.out.print(" Autor:");
            autores.stream().forEach(a -> System.out.print(a.getName()+" "));
            System.out.println();
            System.out.println(" Idioma: "+String.join(", ", libro.getLanguages()));
            System.out.println(" Numero de descargas: "+libro.getDownload_count());
            System.out.println();


        }
        catch (Exception e){
            System.out.println("No existe ese libro");
        }


    }

    private void listarLibrosRegistrados() {
        List<Book> libro = repository.findAll();

        for(Book b: libro){
            System.out.println("----- LIBRO -----");
            System.out.println(" Titulo: "+b.getTitle());
            System.out.print(" Autor:");
            autores.stream().forEach(a -> System.out.print(a.getName()+" "));
            System.out.println();
            System.out.println(" Idioma: "+String.join(", ", b.getLanguages()));
            System.out.println(" Numero de descargas: "+b.getDownload_count());
        }

    }

    private void listarAutoresRegistrados(){
        List<Author> autores = authorRepository.findAll();
        Set<Book> libros;
        for(Author a: autores){
            System.out.println("----------------------");
            System.out.println("Autor: "+ a.getName());
            System.out.println("Fecha de nacimiento: "+ a.getBirth_year());
            System.out.println("Fecha de fallecimiento: "+a.getDeath_year());
            System.out.print("Libros: [");
            libros = a.getBooks();
            for(Book b: libros){
                System.out.print(b.getTitle());
                System.out.print(", ");
            }
            System.out.println("]");
        }
}

    private void listarAutoresVivos(){
        System.out.println("Ingresa el año vivo de autor(es) que desea buscar");
        Integer year = teclado.nextInt();
        Set<Author> autores = authorRepository.findAutoresVivosSegunAnio(year);
        Set<Book> libros ;
        for(Author a: autores){
            System.out.println("----------------------");
            System.out.println("Autor: "+ a.getName());
            System.out.println("Fecha de nacimiento: "+ a.getBirth_year());
            System.out.println("Fecha de fallecimiento: "+a.getDeath_year());
            System.out.print("Libros: [");
            libros = a.getBooks();
            for(Book b: libros){
                System.out.print(b.getTitle());
                System.out.print(", ");
            }
            System.out.println("]");
        }

    }

    private void listarLibrosIdioma(){
        System.out.println("Que idioma deseas buscar");
        System.out.println("""
                es - español
                en - ingles
                fr - frances
                pt - portugues
                """);
        var idioma = teclado.nextLine();
        List<Book> libros = repository.findAutorPorIdioma(idioma);
        for(Book b: libros){
            System.out.println("----- LIBRO -----");
            System.out.println(" Titulo: "+b.getTitle());
            System.out.print(" Autor:");
            autores.stream().forEach(a -> System.out.print(a.getName()+" "));
            System.out.println();
            System.out.println(" Idioma: "+String.join(", ", b.getLanguages()));
            System.out.println(" Numero de descargas: "+b.getDownload_count());
        }
    }

    private BookDTO getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+nombreLibro.trim());
        if(json == null){
            return null;
        }
        BookDTO libro = conversor.obtenerPrimerResultado(json, BookDTO.class);
        return libro;
    }

}
