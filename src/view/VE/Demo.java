package view.VE;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Demo extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/home.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Gerenciador de Tarefas");
        try {
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/notes.png")));
        } catch (Exception e) {
            System.err.println("Ícone não encontrado. Verifique o caminho em /resources/assets/notes.png");
        }

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}