package main.java.io;

import main.java.io.AIO.Server.AsyncTimerServerHandler;

import java.util.Date;

public class TimeServer {
    public static void main(String[] args) {
        int port =8080;
        //基础io实现（BIO）
//        ServerSocket server = null;
//        try {
//            server = new ServerSocket(port);
//            Socket socket = null;
//            while (true){
//                socket = server.accept();
//                new Thread(new TimeServerHandler(socket)).start();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //NIO实现
//        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
//        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();

        //AIO实现
//        AsyncTimerServerHandler timerServerHandler =new AsyncTimerServerHandler(port);
//        new Thread(timerServerHandler, "AIO-AsyncTimerServerHandler-001").start();

        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
        TimeServer a = new TimeServer();
        a.printArray(doubleArray);

// invoke generic printArray method with a Character array
        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };
        a.printArray(charArray);

        Date date=new Date();
        System.out.printf("%td" , date);

    }

    public <E> void printArray( E[] inputArray ) {
        // Display array elements
        for ( E element : inputArray ) {
            System.out.printf( "%s ", element );
        }
        System.out.println();
    }

}
