package com.example.demo;

import com.example.demo.DbFunctions.DbFunctions;
import com.example.demo.DbFunctions.Variables;
import com.example.demo.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainController {
    @FXML
    private ImageView update;

    @FXML
    private TableColumn<User, String> columnId, columnIdPassword,columnIdRole, columnIdStatus,columnLogin;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<User> tableUser;
    private final DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void backScreen() {
        dbFunctions.update_status("Офлайн", Variables.ACTIVE_USER);
        new  Loader().openNewScene(rootPane, "/com/example/demo/hello-view.fxml", "Авторизация");
        Variables.ROLE_USER = "";
        Variables.ACTIVE_USER = "";
    }

    @FXML
    void updateScreen() {
      tableUser.setItems(dbFunctions.getAllUsers());
    }
    @FXML
    void initialize(){
        tableUser.getSortOrder().stream().sorted();
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        columnIdPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        columnIdRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        columnIdStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableUser.setItems(dbFunctions.getAllUsers());

        if (Variables.ROLE_USER.equals("Пользователь")){
            tableUser.setOnMouseClicked(e->{
                if (e.getClickCount() == 2){
                    User user = tableUser.getSelectionModel().getSelectedItem();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/update_user-screen.fxml"));
                        Parent parent = loader.load();
                        UpdateUserController updateUserController = loader.getController();
                        updateUserController.setData(user.getLogin(), user.getPassword(), user.getRole(), user.getId());
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(new Scene(parent));
                        stage.setTitle("Редактирование пользователя");
                        stage.showAndWait();

                    }catch (IOException ex){
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
    }



}
