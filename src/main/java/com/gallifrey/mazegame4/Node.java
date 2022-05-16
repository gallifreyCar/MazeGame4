package com.gallifrey.mazegame4;

//节点
class Node {
    int FValue;//总代价
    int HValue;//预估代价 当前点到终点的的代价
    int GValue;//当前代价 当前点到起点的代价

    int x, y;//x坐标，y坐标
    int pre;//上个节点
    int id = 0;//编号
    boolean isVisited=false;//是否访问
    int value=1;//1是墙，0是路

    public Node(){
    }
    public Node(int x, int y, int pre) {
        this.x = x;
        this.y = y;
        this.pre = pre;

    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPre() {
        return pre;
    }

    public void setPre(int pre) {
        this.pre = pre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setHValue(Node end) {
        //曼哈顿计算H值
           HValue= Math.abs(x-end.getX())+Math.abs(y- end.getY());

    }

    public void setGValue(Node start) {
        //曼哈顿计算G值
        HValue= Math.abs(x-start.getX())+Math.abs(y- start.getY());
    }

    public int getFValue() {
        FValue=HValue+GValue;
        return FValue;
    }

    @Override
    public String toString(){
        return new String(x+" "+y);
    }
}
