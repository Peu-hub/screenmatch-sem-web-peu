package br.com.peu.screenmatch;

import br.com.peu.screenmatch.models.DadosEpisodio;
import br.com.peu.screenmatch.service.ConsumoAPI;
import br.com.peu.screenmatch.service.ConverteDados;
import br.com.peu.screenmatch.models.DadosSeries;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        var consumoAPI = new ConsumoAPI();
        var json = consumoAPI.obterDados("http://www.omdbapi.com/?t=the+clone+wars&apikey=6bffa866");
        System.out.println("\n"+json+"\n");
        ConverteDados conversor = new ConverteDados();
        DadosSeries dados = conversor.obterDados(json, DadosSeries.class);
        System.out.println(dados+"\n");
        json = consumoAPI.obterDados("http://www.omdbapi.com/?t=the+clone+wars&season=1&episode=1&apikey=6bffa866");
        var dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
        System.out.println(dadosEpisodio+"\n");
    }
}
