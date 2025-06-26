package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Tarefa;

public class BlocoNotasController {

    @FXML
    private TextArea areaTexto;

    private Tarefa tarefaAtual;

    public void initData(Tarefa tarefa) {
        this.tarefaAtual = tarefa;
        if (tarefa.getNotas() != null) {
            areaTexto.setText(tarefa.getNotas());
        }
    }

    @FXML
    void salvarEVoltar(ActionEvent event) {
        if (tarefaAtual != null) {
            tarefaAtual.setNotas(areaTexto.getText());
        }
        fecharJanela();
    }

    @FXML
    void limparTexto(ActionEvent event) {
        this.areaTexto.clear();
    }

    @FXML
    void cancelar(ActionEvent event) {
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) areaTexto.getScene().getWindow();
        stage.close();
    }
}