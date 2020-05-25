package stu.java;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        int r = 100;//半径
        double[] o = new double[]{50.00,50.00};//圆心
        double[] x1 = new double[360];
        double[] y1 = new double[360];
        for(int i=0;i<360;i++){//调整i的步长来求div的分布点坐标
            double angle = Math.PI / 180 * i;
            x1[i]=o[0]+r*Math.sin(angle);
            y1[i]=o[1]+r*Math.cos(angle);
        }
        System.out.println(x1);
        System.out.println(y1);
    }
}
