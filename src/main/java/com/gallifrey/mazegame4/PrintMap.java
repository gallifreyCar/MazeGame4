package com.gallifrey.mazegame4;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//import java.sql.ResultSet;

//画地图类
public class PrintMap extends MainPrint{

    public PrintMap(Group group, MainMap mainMap) {
        super(group, mainMap);
    }

    public void print(){

        for (int i = 0; i<mainMap.getRow(); i++){
            for(int j=0;j<mainMap.getCol();j++){
                if(mainMap.nodeMap[i][j].getValue()==1) {
                    Rectangle rec = new Rectangle(OneCol, OneRow, Color.rgb(1, 10, 2));
                    rec.setX(j * (OneCol));
                    rec.setY(i * (OneRow));
                    group.getChildren().add(rec);
                }
            }
        }

        Rectangle start=new Rectangle(OneCol,OneRow,Color.RED) ;
        Rectangle end=new Rectangle(OneCol,OneRow,Color.RED) ;
        start.setX(mainMap.getStart().y*OneCol);
        System.out.println("起点"+start.getX());
        start.setY(mainMap.getStart().x*OneRow);
        end.setX(mainMap.getEnd().y*OneCol);
        end.setY(mainMap.getEnd().x*OneRow);
        group.getChildren().add(end);
        group.getChildren().add(start);
    }
}
