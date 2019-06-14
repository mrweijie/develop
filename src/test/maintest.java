package test;

import java.util.*;

public class maintest {
    public static void main(String[] args) {
        int n = 15;
        maintest maintest = new maintest();
        for(int i = 0; i < n; i++){
           new Thread(new myThread(i , n)).start();
//            new Thread(new myThread2(i, n, 100)).start();
        }
    }


}

class myThread implements Runnable {
    private static final Object LOCK = new Object();

    public static int num = 0;

    public int threadNo;

    public int threadCout;

    public myThread(int threadNo , int threadCout){
        this.threadNo = threadNo;
        this.threadCout = threadCout;
    }

    @Override
    public void run() {
        while (true){
            synchronized (LOCK){
                while (num % threadCout != threadNo){
                    if(num > 100){
                        break;
                    }
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 最大值跳出循环
                if (num > 100) {
                    break;
                }
                System.out.println("Thread - "+ threadNo +" : "+num);
                num++;
                LOCK.notifyAll();
            }
        }
    }
}