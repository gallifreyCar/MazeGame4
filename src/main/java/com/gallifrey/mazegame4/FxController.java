package com.gallifrey.mazegame4;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import static com.gallifrey.mazegame4.ButtonChose.chosedMap;
import static com.gallifrey.mazegame4.HelloApplication.buttonChose;

public class FxController {
    @FXML
    private ComboBox choseMap;


    public void onHelloButtonClick(ActionEvent actionEvent) {
    }

    public void playGame(ActionEvent actionEvent) {


        buttonChose.play2(HelloApplication.scene,HelloApplication.group);
    }

    public void Option(ActionEvent actionEvent){

    }

    public void chosingMap(){

        if(choseMap.getSelectionModel().getSelectedItem().equals("DFS")){
            chosedMap=new DfsMap(41,41);
                    System.out.println("dfs");
        }
        if(choseMap.getSelectionModel().getSelectedItem().equals("Prim")){
            chosedMap=new PrimMap(41,41);
                    System.out.println("prim");

        }



    }




}
