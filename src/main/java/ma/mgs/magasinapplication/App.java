package ma.mgs.magasinapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 962, 539);
        stage.setResizable(false);
        stage.setTitle("Magasin");
        stage.setScene(scene);
        stage.show();
    }
}
