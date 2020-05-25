package test;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class maintest {
    public static void main(String[] args) {
        maintest a = new maintest();
        List<Thread> list = new ArrayList<Thread>();
        for (int i = 0; i < 50; i++) {
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    a.SendString(Thread.currentThread().getName());
                }
            }));
        }

        for (int i = 0; i < list.size(); i++) {
            list.get(i).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private  ReentrantLock lock = new ReentrantLock(true);
    private void SendString(String value) {
        try{
            lock.lock();
            System.out.println(value);
        }finally {
            lock.unlock();
        }
    }
}