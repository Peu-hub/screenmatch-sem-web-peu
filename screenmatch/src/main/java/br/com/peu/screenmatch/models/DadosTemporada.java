package br.com.peu.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(@JsonAlias("Title") String titulo,
                             @JsonAlias("Season") Integer temporada,
                             @JsonAlias("Episodes") List<DadosEpisodio> dadosEpisodios) {

    @Override
    public String toString() {
        return "\nSérie: "+titulo+"\n"+
                "Temporada: "+temporada+"\n"+
                "Episódios"+dadosEpisodios;
    }
}
