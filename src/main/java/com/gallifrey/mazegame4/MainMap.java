package com.gallifrey.mazegame4;

public class MainMap {
    protected Node start;//起点
    protected Node end;//终点
    protected int col;//列数
    protected int row;//行数
    protected Node nodeMap[][];//地图

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public MainMap(int col, int row) {
        this.col = col;
        this.row = row;
        nodeMap=new Node[row][col];

    }

    //初始化
    public void InitMap(){
        // 设置全为墙
        for (int i=0;i<row;i++){
            for (int j=0;j< col;j++){
                Node temp=new Node(i,j);
                nodeMap[i][j]=temp;
            }
        }


        /**随机生成起点和终点
         * 避免起点和终点靠得太近，将地图四分起点只在左上生成，终点在右下生成
         * 起点和终点不能同时在边界处
         */
        start=new Node(((int)(Math.random()*row/2+1)),((int)(Math.random()*col/2+1)));
//        System.out.println("开始点"+start.getX()+" "+start.getY());

        end=new Node((int)((Math.random()*row)/2+row/2),(int)((Math.random()*col))/2+col/2);


    }

    public void makeMap(){

    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }




    public void showMap(){
        System.out.println("起点："+start.getX()+" "+start.getY());
        System.out.println("终点："+end.getX()+" "+end.getY());
        for (int i=0;i<row;i++){
            for (int j=0;j< col;j++){
                System.out.print(nodeMap[i][j].value +" ");
            }
            System.out.println();
        }
    }


    //制作副本
    public MainMap clone(){
        MainMap clone =new MainMap(col,row);
        clone.InitMap();
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                clone.nodeMap[i][j].setValue(nodeMap[i][j].getValue());
                clone.nodeMap[i][j].setPre(nodeMap[i][j].getPre());
                clone.nodeMap[i][j].setVisited(nodeMap[i][j].isVisited());
                clone.nodeMap[i][j].setX(nodeMap[i][j].getX());
                clone.nodeMap[i][j].setY(nodeMap[i][j].getY());
                clone.nodeMap[i][j].setId(nodeMap[i][j].getId());
            }
        }
        clone.end=end;
        clone.start=start;
        return clone;
    }

    public static void main(String[] args) {
        DfsMap dfsMap=new DfsMap(8,8);
        dfsMap.makeMap();
        MainMap clone=dfsMap.clone();
        clone.showMap();
    }
}
