package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class maintest {
    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        System.out.println("请输入一个奇数：");
//        int a=sc.nextInt();
//        int num=(a/2)+1;
//        int[] b=new int[num];//存放需要打印的数据
//        b[0]=a;//第1个值1
//        for(int i=1;i<num;i++){
//            b[i]=(int) (b[i-1]+b[i-1]-Math.pow(2, i));
//
//        }
//        for(int i=0;i<num;i++){
//
//            System.out.print(b[i]+" ");//打印
//        }
//        System.out.println();


//        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        long a = System.currentTimeMillis();
        long b = a/1000L;
        long c = a/1000L + 2208988800L;
        System.out.println(a);
        System.out.println(new Date(a));
        System.out.println(b);
        System.out.println(new Date(b));
        System.out.println(c);
        System.out.println(new Date(c));
    }

}
