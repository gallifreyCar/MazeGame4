package com.gallifrey.mazegame4;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HelloApplication extends Application {

    static Scene scene;
    static Group group;
    static ButtonChose buttonChose;


    public void start(Stage stage) throws IOException {
        group=new Group();
        scene = new Scene(group, 400 ,600);
        stage.setScene(scene);
        buttonChose = new ButtonChose();
        buttonChose.MainMenu2(scene,group);
        stage.show();
        }

    public static void main(String[] args) {

        launch();
    }



}