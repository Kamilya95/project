package com.example.demo;

import com.example.demo.DbFunctions.DbFunctions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class UpdateUserController {



    @FXML
    private Button buttonRemove;

    @FXML
    private Button buttonSave;

    @FXML
    private TextField fieldLogin;

    @FXML
    private TextField fieldPassword;

    @FXML
    private TextField fieldRole;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label labelError;

    DbFunctions dbFunctions = new DbFunctions();
    String idUser = "";

    @FXML
    void  initialize(){
        buttonSave.setOnAction(e -> updateDataUser());
        buttonRemove.setOnAction(e -> deleteDataUser());
    }

    private void deleteDataUser() {
        if(idUser.equals("")){
            labelError.setText("повторите попытку позже");
        }else {
            dbFunctions.deleteDataUser(idUser);
            buttonRemove.getScene().getWindow().hide();

    }}

    private void updateDataUser() {
        String login = fieldLogin.getText();
        String password = fieldPassword.getText();
        String role = fieldRole.getText();
        int codeError = dbFunctions.check_login(login);

        if(login.isEmpty()){
            labelError.setText("Login is Empty");

        } else if (password.isEmpty()){
            labelError.setText("Password is Empty");
        }
        else if (role.isEmpty()){
            labelError.setText("Role is Empty");
        }
        else if (idUser.equals("")){
            labelError.setText("повторите попытку");
        }
        else if (codeError == 0){
            labelError.setText("такой код уже есть");
        }
        else if (codeError == 404){
            labelError.setText("какая-то ошибка");
        }
        else {
            dbFunctions.updateDataUser(login, password, role, idUser);
            buttonSave.getScene().getWindow().hide();
            String log = fieldLogin.getText();
            String pass = fieldPassword.getText();
            if (login.equals("")) {
                buttonSave.setOnMouseClicked(e -> {
                    AnchorPane anchorPane = null;
                    try {
                        anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/demo/authorisation-screen.fxml")));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    rootPane.getChildren().setAll(anchorPane);
                });
            }
        }
        
    }
    public void setData(String login, String password,String role, String id){
        fieldLogin.setText(login);
        fieldPassword.setText(password);
        fieldRole.setText(role);
        idUser = id;

    }
}
