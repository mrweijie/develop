package stu.java.io.BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/*
* 最基础的io
* */
public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(),true);
            String currenTime =null;
            String body = null;
            while(true){
                body = in.readLine();
                if(body == null)
                    break;
                System.out.println("this time server receive order is :"+ body);
                currenTime = "abc".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "error";
                out.println(currenTime);
            }
        } catch (Exception e) {
            if(in != null){
                try{
                    in.close();
                }catch (IOException e1){
                    e1.printStackTrace();
                }
            }
            if(out != null){
                out.close();
                out = null;
            }
            if(this.socket != null){
                try {
                    this.socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.socket = null;
            }
        }
    }
}
