import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Demo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private static Stage stg;

    public static void setStg(Stage stg){ Demo.stg = stg; }

    @Override
    public void start(Stage arg0) throws Exception {
        stg = arg0;
        stg.show();
        iniciar();
    }

    public void iniciar() throws IOException {
        Parent root = FXMLLoader.load(Demo.class.getResource("/view/blocoNotas.fxml"));
        Scene cena = new Scene(root);
        stg.setTitle("Bloco de notas");
        stg.setScene(cena);
    }

    public static void fechar(){
        stg.close();
    }
}
