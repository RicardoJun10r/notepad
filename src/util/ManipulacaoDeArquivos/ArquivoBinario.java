package util.ManipulacaoDeArquivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import util.Interfaces.List.InterList;
import util.Interfaces.ManipularArquivos.Arquivos;
import util.List.DuplamenteEncadeada.ListaDuplamenteEncadeada;

/**
 * <h1>ArquivoBinario</h1> é uma classe {@code ArquivoBinario} que extende e
 * implementa os métodos da interface {@link Arquivos}.
 * <p>
 * Nesse sentido, esta classe é usada para leitura e escrita de arquivos
 * binários
 * </p>
 */
public class ArquivoBinario extends Arquivos {

    public ArquivoBinario() {
    }

    public ArquivoBinario(String path, ListaDuplamenteEncadeada<Object> texto) {
        super(path, texto);
    }

    public void escrever() throws IOException {
        File file = new File(getPATH());
        file.delete();
        file.createNewFile();

        ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(file));
        objOutput.writeObject(getListaObjetos());
        objOutput.close();
    }

    @SuppressWarnings("unchecked")
    public InterList<Object> ler() throws IOException, ClassNotFoundException {

        InterList<Object> lista = new ListaDuplamenteEncadeada<>();

        File file = new File(getPATH());

        if (file.exists()) {
            ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(file));
            lista = (ListaDuplamenteEncadeada<Object>) objInput.readObject();
            objInput.close();
        }
        return lista;

    }

}
