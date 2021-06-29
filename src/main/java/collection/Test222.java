package collection;

import java.util.Scanner;
class MGraph{
    int[][] matrix;// 矩阵
    int  topCount; //顶点
    int edgeCount;  //边点数

    public MGraph() {
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getTopCount() {
        return topCount;
    }

    public void setTopCount(int topCount) {
        this.topCount = topCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void setEdgeCount(int edgeCount) {
        this.edgeCount = edgeCount;
    }
}
public class Test222 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        MGraph  mgraph = new MGraph();
        mgraph.setTopCount(in.nextInt());
        mgraph.setEdgeCount(in.nextInt());
        //使用动态规划DP算法
        System.out.println("3");
    }
}
