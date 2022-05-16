package com.gallifrey.mazegame4;



import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BFS implements SF{

    private LinkedList<Node> linkedList;
    private LinkedList<Node> linkedList2;
    private int front=-1;
    private int rear=-1;
    private Node[][] nodeMap;
    private MainMap mainMap;

    public BFS(MainMap mainMap) {
        this.mainMap = mainMap;
    }



    //查找
    public boolean find(){
        Node start= mainMap.getStart();
        Node end=mainMap.getEnd();
        start.setPre(-1);
        nodeMap =mainMap.nodeMap;

        linkedList=new LinkedList<>();
        Node temp=null;
        linkedList.offer(start);
        rear++;
        nodeMap[start.getX()][start.getY()].setValue(-1);
        while(!(front==rear)) {
            temp=linkedList.get(++front);
            //判断是不是终点
            if (temp.getX() ==end.getX() && temp.getY() ==end.getY()) {
                return true;
            }
            //遍历4个方向

            Node temp2 = null;
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case 0:
                        temp2 = new Node(temp.x, temp.y - 1);
                        break;
                    case 1:
                        temp2 = new Node(temp.x, temp.y + 1);
                        break;
                    case 2:
                        temp2 =  new Node(temp.x - 1, temp.y);
                        break;
                    case 3:
                        temp2 = new Node(temp.x + 1, temp.y);
                        break;
                }
                //可走进队
                if (temp2.getX()<mainMap.getCol()&&temp2.getX()>0&&temp.getY()>0&&temp2.getY()<mainMap.getRow()&&nodeMap[temp2.getX()][temp2.getY()].getValue()==0) {

                    linkedList.offer(temp2);
                    rear++;
                    temp2.id=linkedList.indexOf(temp2);
                    temp2.setPre(temp.getId());
                    nodeMap[temp2.getX()][temp2.getY()].setValue(-1);
                }
            }
        }

        return false;
    }

    public  boolean find2(){
        linkedList2=new LinkedList<>();
        linkedList2.add(linkedList.get(front));
        int j=0;
        while (linkedList2.get(j).getPre()!=-1){
            linkedList2.add(linkedList.get(linkedList2.get(j).getPre()));
            j++;
        }
        linkedList=linkedList2;
        return true;
    }

    public LinkedList<Node> find3(){

        long time1=new Date().getTime();
        System.out.println(time1);
        find();
        find2();

        long time2=new Date().getTime();
        System.out.println(time2);
        System.out.println(time2-time1);
        return linkedList;
    }


    public void show(){
        for (Node temp: linkedList) {
            System.out.println(" "+temp.getX()+"   "+temp.getY() +" ");
        }
    }

    public static void main(String[] args) {
        DfsMap dfsMap=new DfsMap(10,10);
        dfsMap.makeMap();
        dfsMap.showMap();
        BFS bfs=new BFS(dfsMap);




        System.out.println("-------------------------");


        bfs.find3();

        bfs.show();
    }
}




