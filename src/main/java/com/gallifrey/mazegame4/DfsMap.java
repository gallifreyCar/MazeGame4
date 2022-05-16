package com.gallifrey.mazegame4;

import java.util.LinkedList;

/**
 *
 * 深度优先算法生产迷宫
 */
public class DfsMap extends MainMap {




    public DfsMap(int col, int row) {
        super(col, row);
        nodeMap = new Node[row][col];


    }


    @Override
    public void makeMap(){


        InitMap();
        LinkedList<Node> stackList=new LinkedList<>();

//        1.将起点作为当前迷宫单元并标记为已访问
        Node current=nodeMap[1][1];
        nodeMap[1][1].setVisited(true);
        stackList.add(current);

//        2.当还存在未标记的迷宫单元，进行循环
        while(stackList.size()!=0) {

            //遍历4个方向
            int tempR=0;
            int tempC=0;
            LinkedList<Node> AdjList=new LinkedList<>();
            for(int i=0;i<4;i++){
                switch (i){
                    case 0:tempR=current.getX()-2;tempC=current.getY();break;
                    case 1:tempR=current.getX()+2;tempC=current.getY();break;
                    case 2:tempR=current.getX();tempC=current.getY()+2;break;
                    case 3:tempR=current.getX();tempC=current.getY()-2;break;
                }
                //处理边界问题
                if(!(tempC<col&&tempC>0&&tempR>0&&tempR<row)){
                    continue;
                }
                //未访问过的进入选择
                if(nodeMap[tempR][tempC].isVisited==false){
                   AdjList.add(nodeMap[tempR][tempC]);
                }
            }
//          1.如果当前迷宫单元有未被访问过的的相邻的迷宫单元
            if(AdjList.size()!=0) {
//              1.随机选择一个未访问的相邻迷宫单元
                int randomDir = (int) ((AdjList.size() * Math.random()));
                Node adjNode = AdjList.get(randomDir);
//              2.将当前迷宫单元入栈
                stackList.push(current);
//              3.移除当前迷宫单元与相邻迷宫单元的墙
                adjNode.setValue(0);
                current.setValue(0);
                nodeMap[(adjNode.getX()+current.getX())>>1][(adjNode.getY()+current.getY())>>1].setValue(0);
                nodeMap[(adjNode.getX()+current.getX())>>1][(adjNode.getY()+current.getY())>>1].setVisited(true);

//              4.标记相邻迷宫单元并用它作为当前迷宫单元
                adjNode.setVisited(true);
                current=adjNode;

            }
//          2.如果当前迷宫单元不存在未访问的相邻迷宫单元，并且栈不空
            if(stackList.size()!=0&&AdjList.size()==0) {
//              1.栈顶的迷宫单元出栈
//              2.令其成为当前迷宫单元
                 current=stackList.pop();
            }
        }

        //确保生成一个可达的终点
        while (nodeMap[end.getX()][end.getY()].getValue()==1){
            end=new Node((int)((Math.random()*row)/2+row/2),(int)((Math.random()*col))/2+col/2);
        }

    }



    public static void main(String[] args) {
        DfsMap dfsMap=new DfsMap(9,9);
        dfsMap.makeMap();
        dfsMap.showMap();


    }

}
