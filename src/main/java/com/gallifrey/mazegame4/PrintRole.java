package com.gallifrey.mazegame4;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

//画地图类
public class PrintRole extends MainPrint{
    private Node roleNode;
    MainMap clone;
    PrintPath printPath;
    double jueX;
    double jueY;



    public PrintRole(Group group, MainMap mainMap) {
        super(group,mainMap);
        jueX = mainMap.getStart().getY() *OneCol;
        jueY = mainMap.getStart().getX() *OneRow;

    }



    //画角色 操控角色
    public void print() {

        roleNode=new Node(mainMap.start.getX(),mainMap.start.getY());
        roleNode.setPre(-1);

        Rectangle role = new Rectangle((double) 800 / mainMap.getCol(),OneRow, Color.rgb(0, 90, 9));
        role.setX(mainMap.getStart().getY() *OneCol);
        role.setY(mainMap.getStart().getX() *OneRow);
        group.getChildren().add(role);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(role);
        transition.setDuration(Duration.millis(50));
        transition.setInterpolator(Interpolator.LINEAR);




        role.requestFocus();
        role.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:

                    if (isPeng(mainMap.nodeMap[roleNode.getX()][roleNode.getY() - 1].getValue())) {
                        break;
                    }

                    move(transition,roleNode.getX(), roleNode.getY()-1,-OneCol,0);
                    break;
                case D:
                    if (isPeng(mainMap.nodeMap[roleNode.getX()][roleNode.getY() + 1].getValue())) {

                        break;
                    }
                    move(transition,roleNode.getX(), roleNode.getY()+1,OneCol,0);
                    break;

                case W:
                    if (isPeng(mainMap.nodeMap[roleNode.getX()-1][roleNode.getY() ].getValue())){
                        break;
                    }

                    move(transition,roleNode.getX()-1, roleNode.getY(),0,-OneRow);
                    break;

                case S:
                    if (isPeng(mainMap.nodeMap[roleNode.getX()+1][roleNode.getY()].getValue())) {
                        break;
                    }

                    move(transition,roleNode.getX()+1, roleNode.getY(),0,OneRow);

                    break;

                case K:
                    clone = mainMap.clone();
                    clone.setStart(roleNode);
                    printPath = new PrintPath(group, clone);
                    printPath.RoleTips(new BFS(clone).find3());
                    break;

                case J:
                    clone = mainMap.clone();
                    clone.setStart(roleNode);
                    if(printPath==null) {
                        printPath = new PrintPath(group, clone);

                        printPath.showPath(new AStar(clone).find3());
                    }
                    break;



                case L:
                    clone = mainMap.clone();
                    clone.setStart(roleNode);
                    if(printPath==null) {
                        printPath = new PrintPath(group, clone);
                        printPath.showPath(new BFS(clone).find3());
                    }
                    break;

            }
        });

        role.setOnKeyReleased(e2->{
            switch (e2.getCode()) {
                case K: printPath.clear();

                case J: if (printPath!=null){
                    printPath.clear();
                    printPath=null;}


                case L: if (printPath!=null){
                    printPath.clear();
                    printPath=null;}

            }
        });
    }

    public void move(TranslateTransition transition,int whatX,int whatY,double setX,double setY){

        System.out.println(jueX + " " + jueY);
        transition.setByX(setX);
        transition.setByY(setY);
        jueX += setX;
        jueY +=setY;
        transition.play();
        transition.setOnFinished(e2->{
            roleNode.setX(whatX);
            roleNode.setY(whatY);
        });


    }

    //碰撞检测
    public boolean isPeng(int next) {
        if (next == 1) {
            return true;
        }
        return false;
    }
}
