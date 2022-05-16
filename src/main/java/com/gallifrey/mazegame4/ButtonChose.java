package com.gallifrey.mazegame4;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Screen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ButtonChose  {
    private Scene scene;
    private Group group;
    static  MainMap chosedMap;

    public ButtonChose() {
        chosedMap=new PrimMap(41,41);
    }


    //开始游戏
    public void play2(Scene scene, Group group) {


        group.getChildren().remove(0);

        scene.getWindow().setWidth(800+16);
        scene.getWindow().setHeight(800+39);


        chosedMap.makeMap();
        chosedMap.showMap();



        //画地图
        new PrintMap(group, chosedMap).print();

        //画角色
        PrintRole role= new PrintRole(group,chosedMap);
        role.print();


        //返回按钮
        Button button = new Button("返回主菜单");
        group.getChildren().add(button);
        button.setOnAction(e -> {
            MainMenu2(scene, group);
        });







        //窗口居中
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        scene.getWindow().setX((screenBounds.getWidth() -  scene.getWindow().getWidth())/2 );
        scene.getWindow().setY((screenBounds.getHeight() - scene.getWindow().getHeight())/2);



    }

    //主菜单
    public void MainMenu2(Scene scene, Group group) {

        scene.getWindow().setHeight(639);
        scene.getWindow().setWidth(818);



        group.getChildren().clear();


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Frame.fxml"));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();


        try {
            group.getChildren().add(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }


        scene.getWindow().setX((screenBounds.getWidth() -  scene.getWindow().getWidth())/2 );
        scene.getWindow().setY((screenBounds.getHeight() - scene.getWindow().getHeight())/2);

    }


}