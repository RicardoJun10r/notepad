package controllers;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Tarefa;
import util.Interfaces.List.InterList;
import util.Interfaces.ManipularArquivos.Arquivos;
import util.List.DuplamenteEncadeada.ListaDuplamenteEncadeada;
import util.Log.NaoTemNadaAqui;
import util.ManipulacaoDeArquivos.ArquivoBinario;
import util.ManipulacaoDeArquivos.ArquivoTexto;
import view.VE.Demo;

public class BlocoNotasController {

    @FXML
    private MenuItem abridorDeArquivo;

    @FXML
    private MenuItem voltarHome;

    @FXML
    private MenuItem clear;

    @FXML
    private TextArea areaTexto;

    @FXML
    private MenuItem salvadorDeArquivo;

    private Arquivos arquivos_de_texto = new ArquivoTexto();

    private Arquivos arquivos_binario = new ArquivoBinario();

    private Tarefa tarefa;

    @FXML
    void abrirArquivo(ActionEvent event) {
        Stage stg = new Stage();
        stg.setTitle("Abrir arquivo");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src/tmp"));
        File selectedFile =  fileChooser.showOpenDialog(stg);
        if(selectedFile != null){
            this.arquivos_binario.setPATH(selectedFile.getPath());
        }
        try {
            this.areaTexto.clear();
            this.areaTexto.appendText(this.arquivos_binario.ler().removerElemento().toString());
        } catch (NaoTemNadaAqui e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        Demo.iniciar();
    }

    @FXML
    void limparTexto(ActionEvent event) {
        this.areaTexto.clear();
    }

    @FXML
    void salvarArquivo(ActionEvent event) {
        Stage stg = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Arquivo");
        fileChooser.setInitialDirectory(new File("src/tmp"));
        File file = fileChooser.showSaveDialog(stg);
        if(file != null){
            InterList<Object> texto = new ListaDuplamenteEncadeada<>();
            texto.adicionar(this.areaTexto.getText());
            this.arquivos_binario.setPATH(file.getAbsolutePath());
            this.arquivos_binario.setListaObjetos(texto);
            System.out.println(this.areaTexto.getText());
        }
        try {
            this.arquivos_binario.escrever();
        } catch (NaoTemNadaAqui e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getFile(){
        return this.arquivos_binario.getPATH();
    } 

}
