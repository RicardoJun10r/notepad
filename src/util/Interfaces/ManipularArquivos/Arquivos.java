package util.Interfaces.ManipularArquivos;

import java.io.IOException;

import util.Interfaces.List.InterList;

/**
 * <h1>Arquivos</h1> é uma interface {@code Arquivos} para escrita e leitra de
 * arquivos tanto de texto, quanto arquivos binários.
 * As seguintes classes implementam seus métodos:
 * 
 * @see ArquivoTexto
 * @see ArquivoBinario
 */
public abstract class Arquivos {

    // #region Atributos

    private String PATH;

    private String texto;

    private InterList<Object> listaObjetos;

    // #endregion

    // #region Construtores

    public Arquivos(String path, String texto) {
        this.PATH = path;
        this.texto = texto;
    }

    public Arquivos(String path, InterList<Object> texto) {
        this.PATH = path;
        this.listaObjetos = texto;
    }

    public Arquivos() {
    }

    // #endregion

    // #region Métodos

    /**
     * Método para escrita de arquivos de textos e binários
     * 
     * @return void
     * @throws IOException
     */
    public void escrever() throws IOException {
    }

    /**
     * Método para leitura de arquivos de textos e binários
     * 
     * @return InterList, uma lista contendo os objetos do arquivo lido
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public InterList<Object> ler() throws IOException, ClassNotFoundException {
        return listaObjetos;
    }

    public String getPATH() {
        return PATH;
    }

    public void setPATH(String pATH) {
        PATH = pATH;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public InterList<Object> getListaObjetos() {
        return listaObjetos;
    }

    public void setListaObjetos(InterList<Object> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    // #endregion

}
