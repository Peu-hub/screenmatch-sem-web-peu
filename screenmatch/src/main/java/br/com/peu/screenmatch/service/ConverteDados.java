package br.com.peu.screenmatch.service;

import tools.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {


    private ObjectMapper mapper = new ObjectMapper();

//todo      essa classe passa os dados pra qualquer classe.
    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        return mapper.readValue(json, classe);
    }
}
