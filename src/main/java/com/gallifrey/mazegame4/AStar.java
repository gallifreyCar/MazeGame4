package com.gallifrey.mazegame4;



import java.util.*;

public class AStar implements SF {
    private PriorityQueue<Node> open;//优先队列 重写Node的NodeComparator
    private LinkedList<Node> keptList;//保存列表
    private MainMap mainMap;
    private Node end;
    private Node start;
    private Node[][] nodeMap;
    public AStar(MainMap mainMap) {
        this.mainMap = mainMap;
        end=mainMap.getEnd();
        nodeMap=mainMap.nodeMap;
    }

    public void find(){

        //建立一个优先队列
        open=new PriorityQueue<>(NodeComparator);
        //建一个保存链表
        keptList=new LinkedList<>();


        //起点的当前代价为0 预估代价用曼哈顿公式算
        start=mainMap.getStart();
        start.setPre(-1);
        nodeMap[start.getX()][start.getY()].setValue(-1);
        start.setGValue(start);
        start.setHValue(end);

        open.add(start);
//      开启列表中有节点的话，取出第一个节点，即最小F值的节点

//        int GValue=1;

        while(open.size()!=0){
            Node current=open.poll();

            keptList.offer(current);//保存下来，就可以找到路径了
            current.id=keptList.indexOf(current);
//            判断此节点是否是目标点，是则找到了，跳出
            if(current.getX()==end.getX()&&current.getY()==end.getY()) {break;}


            //遍历4个方向

            Node temp2 = null;
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case 0:
                        temp2 = new Node(current.x, current.y - 1);
                        break;
                    case 1:
                        temp2 = new Node(current.x, current.y + 1);
                        break;
                    case 2:
                        temp2 =  new Node(current.x - 1, current.y);
                        break;
                    case 3:
                        temp2 = new Node(current.x + 1, current.y);
                        break;
                }
                //可走进队
                if (temp2.getX()<mainMap.getCol()&&temp2.getX()>0&&temp2.getY()>0&&temp2.getY()<mainMap.getRow()&&nodeMap[temp2.getX()][temp2.getY()].getValue()==0) {

                    //将四周的点都加入优先队列 算出所有FValue
                    temp2.setGValue(start);
                    temp2.setHValue(end);

                    temp2.setPre(current.getId());
                    nodeMap[temp2.getX()][temp2.getY()].setValue(-1);
                    open.add(temp2);
                }
            }

        }



    }

    public  boolean find2(){
        LinkedList<Node>linkedList2=new LinkedList<>();
        linkedList2.add(keptList.getLast());
        int j=0;
        while (linkedList2.get(j).getPre()!=-1){
            linkedList2.add(keptList.get(linkedList2.get(j).getPre()));
            j++;
        }
        keptList=linkedList2;
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
        return keptList;
    }


    //写比较，方便用优先队列
    public static Comparator<Node> NodeComparator=new Comparator<Node>() {
        @Override
        public int compare(Node node1, Node node2) {
            return node1.getFValue()-node2.getFValue();
        }
    };


    public void show(){
        for (Node temp: keptList) {
            System.out.println(temp.id+" "+temp.getX()+"   "+temp.getY() +" "+temp.getPre());
        }
    }

    public static void main(String[] args) {

        DfsMap dfsMap=new DfsMap(101,101);
        dfsMap.makeMap();
        dfsMap.showMap();
        AStar aStar=new AStar(dfsMap);
        aStar.find3();

    }


}




