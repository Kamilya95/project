package com.example.demo;

import com.example.demo.DbFunctions.DbFunctions;
import com.example.demo.DbFunctions.Variables;
import com.example.demo.Models.SceneModel;
import com.example.demo.Models.StageModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Loader extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        StageModel.setMyStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(Loader.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        SceneModel.setMyScene(scene);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop(){
        new DbFunctions().update_status("офлайн", Variables.ACTIVE_USER);
    }


    public void openNewScene(AnchorPane root, String window, String title){

        try{
            AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(window)));
            root.getChildren().setAll(anchorPane);
            StageModel.getMyStage().setTitle(title);
        } catch (IOException e ){
            System.out.println(e.getMessage());

        }
}}

