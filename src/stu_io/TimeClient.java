package stu_io;

import stu_io.NIO.TimeClientHandle;

public class TimeClient {
    public static void main(String[] args) {
        int port = 8001;
        new Thread(new TimeClientHandle("127.0.0.1",port),"TimeClient-001").start();
    }
}
