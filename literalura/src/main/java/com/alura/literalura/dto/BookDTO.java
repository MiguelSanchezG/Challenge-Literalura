package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        @JsonAlias("title") String titulo,
        @JsonAlias("languages") Set<String> idioma,
        @JsonAlias("download_count") Double numeroDescargas,
        @JsonAlias("authors") List<AuthorDTO> authors

) {
}
