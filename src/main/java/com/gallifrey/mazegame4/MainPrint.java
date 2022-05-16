package com.gallifrey.mazegame4;

import javafx.scene.Group;

public class MainPrint {
    protected Group group;
    protected MainMap mainMap;
    protected double OneCol;
    protected double OneRow;
    public MainPrint(Group group,MainMap mainMap) {
        this.group = group;
        this.mainMap = mainMap;
        OneCol=(double) 800 / mainMap.getCol();
        OneRow=(double) 800/mainMap.getRow();
    }
}
