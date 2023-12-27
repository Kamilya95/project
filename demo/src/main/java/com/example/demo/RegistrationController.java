package com.example.demo;

import com.example.demo.DbFunctions.DbFunctions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.SepiaTone;
import javafx.scene.layout.AnchorPane;
import com.example.demo.Models.StageModel;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.w3c.dom.events.MouseEvent;

import java.util.Set;

public class RegistrationController {

    @FXML
    private Button buttonAuth;

    @FXML
    private TextField fieldLogin;

    @FXML
    private TextField fieldPassword;

    @FXML
    private Label labelAuthorization;
    private final DbFunctions dbFunctions= new DbFunctions();
    @FXML
    private AnchorPane rootPane;

    @FXML
    void backScreen() {

    rootPane.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {
        buttonAuth.setOnAction(e -> validation());
    }

    private void validation() {
        Stage stage = StageModel.getMyStage();
        String login = fieldLogin.getText();
        String password = fieldPassword.getText();
        int codeError = dbFunctions.signIn(login, password);
        if (login.isEmpty()){
            labelAuthorization.setText("Login Empty");
        } else if(password.isEmpty()){
            labelAuthorization.setText("Password empty");
        }else if(codeError == 0){
            labelAuthorization.setText("Не найден аккаунт");
        }else if(codeError == 404){
            labelAuthorization.setText("ошибка");
        }else {
            labelAuthorization.setText("");

            new Loader().openNewScene(rootPane, "/com/example/demo/main-screen.fxml", "Главное меню");
            dbFunctions.update_status("онлайн", login);
        }
    }

}