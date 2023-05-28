package ma.mgs.magasinapplication.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.mgs.magasinapplication.App;
import ma.mgs.magasinapplication.entities.User;
import ma.mgs.magasinapplication.service.UserService;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    public void initialize() {
        // Ajoutez ici la logique associée aux éléments d'interface utilisateur du formulaire de login

        loginButton.setOnMouseClicked(event -> {
            if ( auth(constructionUser())){
                try {

                    Stage stage1 = (Stage) loginButton.getScene().getWindow();
                    stage1.close();
                    Stage stage= new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("add-magasin-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 934 , 513);
                    stage.setResizable(false);
                    stage.setTitle("Magazin App");
                    stage.setScene(scene);

                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information");
                alert.setHeaderText("Auth erreur");
                alert.setContentText("Veuillez saisir les informations d'authentification .");
                alert.showAndWait();

            }


        });





        cancelButton.setOnAction(event -> {

            System.exit(0);
        });
    }
    public boolean auth(User user){
        UserService userService = new UserService();
        return userService.auth(user);
    }
    public User constructionUser(){
        User user = new User();
        if(Username.getText().isEmpty() || Password.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Auth erreur");
            alert.setContentText("Veuillez saisir les informations d'authentification .");
            alert.showAndWait();
        }
        else {
            user.setPassword(Password.getText());
            user.setUsername(Username.getText());
        }
        return user;
    }

}


