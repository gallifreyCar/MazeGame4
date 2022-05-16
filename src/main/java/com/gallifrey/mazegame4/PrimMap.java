package com.gallifrey.mazegame4;

import java.util.LinkedList;

/**
 *
 * 普利姆算法生产迷宫
 */
public class PrimMap extends MainMap {


    public PrimMap(int col, int row) {
        super(col, row);

    }

    //默认根节点(1,1) 因为是最小生成树，所以随机根节点意义不带 设置默认根节点可以减少一次遍历（手动输入根节点的四周遍历）
    public void makeMap(){
        InitMap();
        nodeMap[1][1].setValue(0);//将起点变成路

        LinkedList<Node> listA=new LinkedList<>();
        LinkedList<Node> listB= null;
        int tempR = 0;
        int tempC = 0;
        //待选路点进入链表
        Node temp=nodeMap[1][3];
        listA.add(temp);
        temp=nodeMap[3][1];
        listA.add(temp);

        while (listA.size()!=0){

            int randomNodeId=(int)(listA.size()*Math.random());//随机备选点作为A
            Node randomA=listA.get(randomNodeId);//随机路点A
            listB=new LinkedList<>();
            boolean flag=true;
            //将选路点A四周是路的进入备选池
            for (int i=0;i<4;i++){
                switch (i){
                    case 0:tempR=randomA.getX()-2;tempC=randomA.getY();break;
                    case 1:tempR=randomA.getX()+2;tempC=randomA.getY();break;
                    case 2:tempR=randomA.getX();tempC=randomA.getY()+2;break;
                    case 3:tempR=randomA.getX();tempC=randomA.getY()-2;break;
                }
                if(!(tempC<col&&tempC>=0&&tempR>=0&&tempR<row)){
                    continue;
                }
                //判断是否通路    加入临时通路备选池B
                if(nodeMap[tempR][tempC].getValue()==0){
                    temp=nodeMap[tempR][tempC];
                    listB.add(temp);// listB其实用数组更好 ，大小只有0-3 有1个是来的点，肯定访问过了
                }
                //判断是否为墙和A中是否包含该点加入 待选路点链表listA
                else if(nodeMap[tempR][tempC].getValue()==1&&!listA.contains(nodeMap[tempR][tempC])){
                        temp=nodeMap[tempR][tempC];
                        listA.add(temp);
                }
            }

            int randomPass=(int)(listB.size()*Math.random());//随机备选点作为B
            Node randomB=listB.get(randomPass);//随机通路点
            //打通一条通路
            nodeMap[randomA.getX()][randomA.getY()].setValue(0);
            nodeMap[(randomB.getX()+randomA.getX())>>1][(randomA.getY()+randomB.getY())>>1].setValue(0);
            listA.remove(randomA);
        }

        //确保生成一个可达的终点
        while (nodeMap[end.getX()][end.getY()].getValue()==1){
            end=new Node((int)((Math.random()*row)/2+row/2),(int)((Math.random()*col))/2+col/2);
        }
    }

    public static void main(String[] args) {
        PrimMap primMap=new PrimMap(11,11);
        primMap.makeMap();
        primMap.showMap();


    }

}
