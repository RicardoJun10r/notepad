package util.ManipulacaoDeArquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import util.Interfaces.List.InterList;
import util.Interfaces.ManipularArquivos.Arquivos;
import util.List.DuplamenteEncadeada.ListaDuplamenteEncadeada;

/**
 * <h1>ArquivoTexto</h1> é uma classe {@code ArquivoTexto} que extende e
 * implementa os métodos da interface {@link Arquivos}.
 * <p>
 * Nesse sentido, esta classe é usada para leitura e escrita de arquivos de
 * texto
 * </p>
 */
public class ArquivoTexto extends Arquivos {

	public ArquivoTexto(String path, String texto) {
		super(path, texto);
	}

	public ArquivoTexto() {
	}

	public void escrever() throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getPATH()));
		bufferedWriter.append(getTexto());
		bufferedWriter.close();
	}

	public InterList<Object> ler() throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader(getPATH()));

		InterList<Object> lista = new ListaDuplamenteEncadeada<>();

		StringBuffer sbResult = new StringBuffer();
		String linha = "";

		while (linha != null) {
			sbResult.append(linha + "\n");
			linha = bufferedReader.readLine();

			if (linha != null) {
				String[] parts = linha.split(";");
				for (int i = 0; i < parts.length; i++) {
					System.out.print("[" + parts[i] + "] ");
				}
			}

		}
		bufferedReader.close();

		lista.adicionar(sbResult);

		return lista;

	}

}
