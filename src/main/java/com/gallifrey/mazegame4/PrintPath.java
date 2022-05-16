package com.gallifrey.mazegame4;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.LinkedList;

public class PrintPath extends MainPrint{
    private Group tempGroup;
    private Polyline polyline;



    public PrintPath(Group group, MainMap mainMap) {
        super(group,mainMap);
        tempGroup=new Group();
        group.getChildren().add(tempGroup);
    }

    // 自动导航
    void AutoDrive(LinkedList<Node> linkedList){
        //走迷宫小方块
        Rectangle litleRec=new Rectangle(OneCol,OneRow, Color.rgb(0,90,9));

        group.getChildren().add(litleRec);
        TranslateTransition transition=null;
        SequentialTransition sequentialTransition =new SequentialTransition();
        polyline=new Polyline();
        polyline.setStroke(Color.rgb(253,93,53));
        polyline.setStrokeWidth(0.6*OneRow);
        group.getChildren().add(polyline);

        for(int i= linkedList.size()-1;i-1>=0;i--) {
//            System.out.println(linkedList.get(i).getId()+" "+linkedList.get(i).getX()+" "+linkedList.get(i).getY()+" "+linkedList.get(i).pre);
            transition = new TranslateTransition();
            transition.setNode(litleRec);
            transition.setDuration(Duration.seconds(0.1));
            transition.setFromY(linkedList.get(i).getX() *OneRow);
            transition.setFromX(linkedList.get(i).getY() * OneCol);
            transition.setToY(linkedList.get(i - 1).getX() * OneRow);
            transition.setToX(linkedList.get(i - 1).getY() * OneCol);

            sequentialTransition.getChildren().add(transition);

        }


        sequentialTransition.setOnFinished(e->{

            int i= linkedList.size()-1;
            polyline.getPoints().addAll((linkedList.get(i).getY() *OneCol+0.5*OneCol),linkedList.get(i).getX() * OneRow+0.5*OneCol);
            for( ;i-1>=0;i--) {
                polyline.getPoints().addAll((linkedList.get(i-1).getY() *OneCol+0.5*OneCol),linkedList.get(i-1).getX() * OneRow+0.5*OneCol);
            }
        });


        sequentialTransition.play();

    }

    // 角色导航提示
    void RoleTips(LinkedList<Node> linkedList){
        int col=mainMap.getCol();
        int row=mainMap.getRow();
        //走迷宫小方块
        Rectangle litleRec=new Rectangle((double) 800 /col,OneRow, Color.rgb(0,90,9));

        group.getChildren().add(litleRec);
        TranslateTransition transition=null;
        SequentialTransition sequentialTransition =new SequentialTransition();
        polyline=new Polyline();
        polyline.setStroke(Color.rgb(253,93,53));
        polyline.setStrokeWidth(0.6*OneRow);
        group.getChildren().add(polyline);

        for(int i= linkedList.size()-1;i-1>=0;i--) {
//            System.out.println(linkedList.get(i).getId()+" "+linkedList.get(i).getX()+" "+linkedList.get(i).getY()+" "+linkedList.get(i).pre);
            transition = new TranslateTransition();
            transition.setNode(litleRec);
            transition.setDuration(Duration.seconds(0.1));
            transition.setFromY(linkedList.get(i).getX() *OneRow);
            transition.setFromX(linkedList.get(i).getY() * OneCol);
            transition.setToY(linkedList.get(i - 1).getX() * OneRow);
            transition.setToX(linkedList.get(i - 1).getY() * OneCol);
            sequentialTransition.getChildren().add(transition);

        }
        sequentialTransition.play();

    }


    //路径规划
    public void showPath(LinkedList<Node> linkedList){

            polyline = new Polyline();
            polyline.setStroke(Color.rgb(253, 93, 53));
            polyline.setStrokeWidth(0.6 *OneRow);
            tempGroup.getChildren().add(polyline);

            int i = linkedList.size() - 1;
            polyline.getPoints().addAll((linkedList.get(i).getY() *OneCol + 0.5 *OneCol), linkedList.get(i).getX() *OneRow + 0.5 *OneCol);
            for (; i - 1 >= 0; i--) {
                polyline.getPoints().addAll((linkedList.get(i - 1).getY() *OneCol + 0.5 *OneCol), linkedList.get(i - 1).getX() *OneRow + 0.5 *OneCol);
            }
    }

    public void clear(){
        group.getChildren().remove(polyline);
        group.getChildren().remove(tempGroup);


    }
}
