package br.com.peu.screenmatch.service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
