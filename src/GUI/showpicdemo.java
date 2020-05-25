package GUI;

import javax.swing.*;
import java.awt.*;

public class showpicdemo extends JFrame {

    static ImageIcon icon = new ImageIcon("E:\\2.jpg");
    static JLabel jLabel = new JLabel(icon);


    public showpicdemo(){
        this.setLayout(new GridLayout(1, 1));
        this.add(jLabel);
        this.setTitle("工程");
        this.setLocation(500, 250);
        this.setSize(500, 500);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void change(byte[] bytes){
        System.out.println(bytes[1]);
//        jLabel.setIcon(new ImageIcon(bytes));
    }

    public static void main(String[] args){
        showpicdemo jsplitPane = new showpicdemo();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

