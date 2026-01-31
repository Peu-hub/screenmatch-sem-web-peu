package br.com.peu.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Title") String titulo,
                            @JsonAlias("Season") int temporada,
                            @JsonAlias("Episode") int episodio,
                            @JsonAlias("imdbRating") String avaliacao) {
}
