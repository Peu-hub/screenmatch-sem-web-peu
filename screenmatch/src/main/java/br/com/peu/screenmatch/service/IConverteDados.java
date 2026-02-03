package br.com.peu.screenmatch.service;

public interface IConverteDados {
//    interface com metodo genérico.
    <T> T obterDados(String json, Class<T> classe);
}
