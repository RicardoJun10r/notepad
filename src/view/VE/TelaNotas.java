package view.VE;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaNotas extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage arg0) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/blocoNotas.fxml"));
        Scene cena = new Scene(root);
        arg0.setTitle("Notepad");
        arg0.setScene(cena);
        arg0.show();
    }
}
