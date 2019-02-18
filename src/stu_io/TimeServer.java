package stu_io;

import stu_io.AIO.Server.AsyncTimerServerHandler;

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
        AsyncTimerServerHandler timerServerHandler =new AsyncTimerServerHandler(port);
        new Thread(timerServerHandler, "AIO-AsyncTimerServerHandler-001").start();

    }

}
