package br.com.peu.screenmatch.principal;

import br.com.peu.screenmatch.models.DadosEpisodio;
import br.com.peu.screenmatch.models.DadosSeries;
import br.com.peu.screenmatch.models.DadosTemporada;
import br.com.peu.screenmatch.service.ConsumoAPI;
import br.com.peu.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY ="&apikey=6bffa866";
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();



    public void exibeMenu(){
        System.out.println("\n---MENU---");
        System.out.println("Digite uma série para buscar: ");
        var serie = sc.nextLine().replace(" ","+");
        var json = consumoAPI.obterDados(ENDERECO+serie+API_KEY);
        DadosSeries dadosSerie = conversor.obterDados(json, DadosSeries.class);
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas() ; i++) {
            json = consumoAPI.obterDados(ENDERECO+serie+"&season="+i+API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        temporadas.forEach(System.out::println);

//        temporadas.forEach(t -> t.dadosEpisodios()
//                .forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> listaEpisodios = temporadas.stream()
                .flatMap(t -> t.dadosEpisodios().stream())
                .collect(Collectors.toList());

        List<DadosEpisodio> melhoresEpisodios = listaEpisodios.stream()
                .filter(e -> !e.avaliacao().equals("N/A")) // remove os "N/A"
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                        .limit(5)
                .collect(Collectors.toList());


        System.out.println("\nMelhores Episódios de "+dadosSerie.titulo()+":");
        melhoresEpisodios.forEach(System.out::println);

    }
}
