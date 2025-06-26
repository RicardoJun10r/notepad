package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Prioridade;
import model.Tarefa;
import service.GerenciadorTarefas;
import util.Interfaces.List.InterList;
import util.Iterator.IterarListaEncadeada;
import view.VE.Demo;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HomeController<E> implements Initializable {

    @FXML
    private TableView<Tarefa> TABELA = new TableView<>();

    @FXML
    private Button botaoAbrirNotas;

    @FXML
    private Button botaoDeletarNotas;

    @FXML
    private TableColumn<Tarefa, LocalDate> col_data;

    @FXML
    private TableColumn<Tarefa, String> col_descricao;

    @FXML
    private TableColumn<Tarefa, UUID> col_id;

    @FXML
    private TableColumn<Tarefa, String> col_nome;

    @FXML
    private TableColumn<Tarefa, Prioridade> col_prioridade;

    @FXML
    private Pane CRIAR_MODAL_PANE;

    @FXML
    private Pane EDITAR_MODAL_PANE;

    @FXML
    private Pane TABELA_PANE;

    @FXML
    private Button atualizarBotaoEditar;

    @FXML
    private Button cancelarBotaoEditar;

    @FXML
    private Button cancelarBottaoAdd;

    @FXML
    private Button criarBotaoAdd;

    @FXML
    private Button criar_icon;

    @FXML
    private Button editar_icon;

    @FXML
    private Button home_icon;

    @FXML
    private Button logout_icon;

    @FXML
    private RadioMenuItem prioridade_alta;

    @FXML
    private RadioMenuItem prioridade_baixa;

    @FXML
    private RadioMenuItem prioridade_media;

    @FXML
    private RadioMenuItem tarefa_alta_editar;

    @FXML
    private RadioMenuItem tarefa_baixa_editar;

    @FXML
    private TextField tarefa_caminho_editar;

    @FXML
    private DatePicker tarefa_data;

    @FXML
    private TextField tarefa_descricao;

    @FXML
    private TextField tarefa_descricao_editar;

    @FXML
    private TextField tarefa_id_editar;

    @FXML
    private RadioMenuItem tarefa_media_editar;

    @FXML
    private TextField tarefa_nome;

    @FXML
    private TextField tarefa_nome_editar;

    private GerenciadorTarefas tarefas = new GerenciadorTarefas();

    private ObservableList<Tarefa> observableTarefas;

    private IterarListaEncadeada<Tarefa> iterarListaEncadeada;

    private List<Tarefa> lista = new ArrayList<>();

    private Parent root; 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarTabelaTarefas();

        this.TABELA.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewTarefas(newValue));
    }

    public void carregarTabelaTarefas(){

        this.col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        this.col_nome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        this.col_descricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        this.col_data.setCellValueFactory(new PropertyValueFactory<>("Data"));
        this.col_prioridade.setCellValueFactory(new PropertyValueFactory<>("Prioridade"));

        try {
            this.observableTarefas = FXCollections.observableArrayList(
                this.tarefas.listar()
            );
            this.TABELA.setItems(observableTarefas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selecionarItemTableViewTarefas(Tarefa tarefa) {
        if (tarefa != null) {
            System.out.println("Tarefa selecionado no TableView: " + tarefa.getNome());
        }
    }

    @FXML
    void abrirNotas(ActionEvent event) throws IOException {
        Tarefa tarefa = TABELA.getSelectionModel().getSelectedItem();
        if (tarefa != null) {
            System.out.println("tarefa removido do TableView: " + tarefa.getNome());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/blocoNotas.fxml"));
            root = loader.load();
            BlocoNotasController blocoNotasController = loader.getController();
            tarefa.setNotas( blocoNotasController.getFile() );
            Demo.notas();
        }
    }

    @FXML
    void deletarNotas(ActionEvent event) {
        Tarefa tarefa = TABELA.getSelectionModel().getSelectedItem();
        if (tarefa != null) {
            System.out.println("tarefa removido do TableView: " + tarefa.getNome());
            TABELA.getItems().remove(tarefa);
        }
    }

    @FXML
    void abrirModalCriar(ActionEvent event) {
        TABELA_PANE.setVisible(false);
        EDITAR_MODAL_PANE.setVisible(false);
        CRIAR_MODAL_PANE.setVisible(true);
    }

    private void addPrioridade(Tarefa task){
        if(this.prioridade_alta.isSelected()) task.setPrioridade(Prioridade.ALTA);
        else if(this.prioridade_media.isSelected()) task.setPrioridade(Prioridade.MEDIA);
        else task.setPrioridade(Prioridade.BAIXA);
    }

    @FXML
    void abrirModalEditar(ActionEvent event) {
        TABELA_PANE.setVisible(false);
        CRIAR_MODAL_PANE.setVisible(false);
        EDITAR_MODAL_PANE.setVisible(true);
    }

    @FXML
    void atualizar(ActionEvent event) {

    }

    @FXML
    void criarModalAdd(ActionEvent event) {
        Tarefa nova_tarefa = new Tarefa();
        nova_tarefa.setNome(this.tarefa_nome.getText());
        nova_tarefa.setDescricao(this.tarefa_descricao.getText());
        this.addPrioridade(nova_tarefa);
        nova_tarefa.setData(this.tarefa_data.getValue());
        nova_tarefa.setNotas(null);
        this.tarefas.adicionarTarefa(nova_tarefa);
        CRIAR_MODAL_PANE.setVisible(false);
        TABELA_PANE.setVisible(true);
        this.tarefa_nome.setText("");
        this.tarefa_descricao.setText("");
        this.prioridade_alta.setSelected(false);
        this.prioridade_media.setSelected(false);
        this.prioridade_baixa.setSelected(false);
        this.tarefa_data.setValue(null);
        this.carregarTabelaTarefas();
    }

    @FXML
    void fechar(ActionEvent event) {
        Demo.fechar();
    }

    @FXML
    void fecharEdicao(ActionEvent event) {
        EDITAR_MODAL_PANE.setVisible(false);
        TABELA_PANE.setVisible(true);
    }

    @FXML
    void fecharModalAdd(ActionEvent event) {
        this.tarefa_nome.setText("");
        this.tarefa_descricao.setText("");
        this.prioridade_alta.setSelected(false);
        this.prioridade_media.setSelected(false);
        this.prioridade_baixa.setSelected(false);
        this.tarefa_data.setValue(null);
        CRIAR_MODAL_PANE.setVisible(false);
        TABELA_PANE.setVisible(true);
    }

    @FXML
    void irParaHome(ActionEvent event) {
        TABELA_PANE.setVisible(true);
        CRIAR_MODAL_PANE.setVisible(false);
        EDITAR_MODAL_PANE.setVisible(false);
    }
}
