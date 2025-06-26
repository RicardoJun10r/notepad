package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Prioridade;
import model.Tarefa;
import service.GerenciadorTarefas;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private TableView<Tarefa> tabelaTarefas;
    @FXML
    private TableColumn<Tarefa, LocalDate> colData;
    @FXML
    private TableColumn<Tarefa, String> colDescricao;
    @FXML
    private TableColumn<Tarefa, String> colNome;
    @FXML
    private TableColumn<Tarefa, Prioridade> colPrioridade;

    @FXML
    private Pane criarModalPane;
    @FXML
    private Pane editarModalPane;
    @FXML
    private Pane tabelaPane;

    @FXML
    private Button botaoAbrirNotas;
    @FXML
    private Button botaoDeletar;
    @FXML
    private Button botaoEditar;

    @FXML
    private TextField tarefaNome;
    @FXML
    private TextField tarefaDescricao;
    @FXML
    private DatePicker tarefaData;
    @FXML
    private ToggleGroup prioridadeGroup;
    @FXML
    private RadioButton prioridadeAlta;
    @FXML
    private RadioButton prioridadeMedia;
    @FXML
    private RadioButton prioridadeBaixa;

    @FXML
    private TextField tarefaNomeEditar;
    @FXML
    private TextField tarefaDescricaoEditar;
    @FXML
    private DatePicker tarefaDataEditar;
    @FXML
    private ToggleGroup prioridadeGroupEditar;
    @FXML
    private RadioButton tarefaAltaEditar;
    @FXML
    private RadioButton tarefaMediaEditar;
    @FXML
    private RadioButton tarefaBaixaEditar;

    private GerenciadorTarefas gerenciadorTarefas;
    private ObservableList<Tarefa> observableTarefas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.gerenciadorTarefas = new GerenciadorTarefas();
        setupTable();
        carregarTabelaTarefas();
        setupBindings();
        irParaHome(null);
    }

    private void setupTable() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colPrioridade.setCellValueFactory(new PropertyValueFactory<>("prioridade"));

        observableTarefas = FXCollections.observableArrayList();
        tabelaTarefas.setItems(observableTarefas);
    }

    private void setupBindings() {
        botaoEditar.disableProperty().bind(tabelaTarefas.getSelectionModel().selectedItemProperty().isNull());
        botaoDeletar.disableProperty().bind(tabelaTarefas.getSelectionModel().selectedItemProperty().isNull());
        botaoAbrirNotas.disableProperty().bind(tabelaTarefas.getSelectionModel().selectedItemProperty().isNull());
    }

    private void carregarTabelaTarefas() {
        observableTarefas.setAll(gerenciadorTarefas.listarTodas());
    }

    @FXML
    void abrirModalCriar(ActionEvent event) {
        limparCamposCriar();
        tabelaPane.setVisible(false);
        editarModalPane.setVisible(false);
        criarModalPane.setVisible(true);
    }

    @FXML
    void criarTarefa(ActionEvent event) {
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setNome(tarefaNome.getText());
        novaTarefa.setDescricao(tarefaDescricao.getText());
        novaTarefa.setData(tarefaData.getValue());
        novaTarefa.setPrioridade(getPrioridadeFromToggle(prioridadeGroup));

        gerenciadorTarefas.adicionarTarefa(novaTarefa);
        observableTarefas.add(novaTarefa);

        irParaHome(null);
    }

    @FXML
    void abrirModalEditar(ActionEvent event) {
        Tarefa tarefaSelecionada = tabelaTarefas.getSelectionModel().getSelectedItem();
        if (tarefaSelecionada == null) {
            exibirAlerta("Nenhuma tarefa selecionada", "Por favor, selecione uma tarefa para editar.");
            return;
        }

        tarefaNomeEditar.setText(tarefaSelecionada.getNome());
        tarefaDescricaoEditar.setText(tarefaSelecionada.getDescricao());
        tarefaDataEditar.setValue(tarefaSelecionada.getData());
        setPrioridadeInToggle(prioridadeGroupEditar, tarefaSelecionada.getPrioridade());

        tabelaPane.setVisible(false);
        criarModalPane.setVisible(false);
        editarModalPane.setVisible(true);
    }

    @FXML
    void atualizarTarefa(ActionEvent event) {
        Tarefa tarefaSelecionada = tabelaTarefas.getSelectionModel().getSelectedItem();
        if (tarefaSelecionada == null)
            return;

        Tarefa tarefaAtualizada = new Tarefa();
        tarefaAtualizada.setNome(tarefaNomeEditar.getText());
        tarefaAtualizada.setDescricao(tarefaDescricaoEditar.getText());
        tarefaAtualizada.setData(tarefaDataEditar.getValue());
        tarefaAtualizada.setPrioridade(getPrioridadeFromToggle(prioridadeGroupEditar));
        tarefaAtualizada.setNotas(tarefaSelecionada.getNotas());

        gerenciadorTarefas.atualizar(tarefaSelecionada.getId(), tarefaAtualizada);

        tabelaTarefas.refresh();

        irParaHome(null);
    }

    @FXML
    void deletarTarefa(ActionEvent event) {
        Tarefa tarefaSelecionada = tabelaTarefas.getSelectionModel().getSelectedItem();
        if (tarefaSelecionada != null) {
            gerenciadorTarefas.remover(tarefaSelecionada.getId());
            observableTarefas.remove(tarefaSelecionada);
        } else {
            exibirAlerta("Nenhuma tarefa selecionada", "Por favor, selecione a tarefa que deseja excluir.");
        }
    }

    @FXML
    void abrirNotas(ActionEvent event) throws IOException {
        Tarefa tarefaSelecionada = tabelaTarefas.getSelectionModel().getSelectedItem();
        if (tarefaSelecionada == null)
            return;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/blocoNotas.fxml"));
        Parent root = loader.load();

        BlocoNotasController notasController = loader.getController();
        notasController.initData(tarefaSelecionada);

        Stage stage = new Stage();
        stage.setTitle("Notas da Tarefa: " + tarefaSelecionada.getNome());
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        tabelaTarefas.refresh();
    }

    @FXML
    void irParaHome(ActionEvent event) {
        tabelaPane.setVisible(true);
        criarModalPane.setVisible(false);
        editarModalPane.setVisible(false);
        tabelaTarefas.getSelectionModel().clearSelection();
    }

    @FXML
    void fecharApp(ActionEvent event) {
        Stage stage = (Stage) tabelaPane.getScene().getWindow();
        stage.close();
    }

    private Prioridade getPrioridadeFromToggle(ToggleGroup group) {
        RadioButton selected = (RadioButton) group.getSelectedToggle();
        if (selected != null) {
            String id = selected.getId();
            if (id.contains("Alta"))
                return Prioridade.ALTA;
            if (id.contains("Media"))
                return Prioridade.MEDIA;
        }
        return Prioridade.BAIXA;
    }

    private void setPrioridadeInToggle(ToggleGroup group, Prioridade prioridade) {
        if (prioridade == null) {
            group.selectToggle(tarefaBaixaEditar);
            return;
        }
        switch (prioridade) {
            case ALTA:
                group.selectToggle(tarefaAltaEditar);
                break;
            case MEDIA:
                group.selectToggle(tarefaMediaEditar);
                break;
            case BAIXA:
            default:
                group.selectToggle(tarefaBaixaEditar);
                break;
        }
    }

    private void limparCamposCriar() {
        tarefaNome.clear();
        tarefaDescricao.clear();
        tarefaData.setValue(null);
        prioridadeGroup.selectToggle(prioridadeBaixa);
    }

    private void exibirAlerta(String titulo, String conteudo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }
}