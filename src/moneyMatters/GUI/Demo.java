package moneyMatters.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Demo extends JFrame {

    // 定义组件
    JPanel jp1, jp2, jp3, jp4, jp5, jp6;
    JLabel jlb1;
    JLabel jlb2;
    JLabel jlb3;
    JLabel jlb4;
    JButton jb1, jb2, jb3;
    JRadioButton jrb1, jrb2;
    JTextField jtf1, jtf2, jtf3;
    JTextArea jta1;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Demo d1 = new Demo();
    }

    // 构造函数
    public Demo() {

        jp1 = new JPanel();

        jlb1 = new JLabel("本金: ");
        jlb2 = new JLabel("年化率: ");
        jlb3 = new JLabel("时间(天): ");
        jlb4 = new JLabel("是否复利： ");

        jb1 = new JButton("计算");
        jb2 = new JButton("取消");
        jb3 = new JButton("清空");
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jrb1.isSelected()){
                    jta1.setText(overlying(Double.parseDouble(jtf1.getText()),Double.parseDouble(jtf2.getText()),Integer.parseInt(jtf3.getText())).toString());
                }
                if(jrb2.isSelected()){
                    jta1.setText(notoverlying(Double.parseDouble(jtf1.getText()),Double.parseDouble(jtf2.getText()),Integer.parseInt(jtf3.getText())).toString());
                }
            }
        });
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtf1.setText("");
                jtf2.setText("");
                jtf3.setText("");
                jrb1.setSelected(true);
            }
        });

        jtf1 = new JTextField(20);
        jtf1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.VK_ESCAPE == e.getKeyCode()) {
                    System.exit(0);
                }
                super.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });
        jtf2 = new JTextField(20);
        jtf3 = new JTextField(20);


        jrb1 = new JRadioButton("是");
        jrb2 = new JRadioButton("否");
        // 一定要把jrb1，jrb2放入到一个ButtonGroup里面
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrb1);
        jrb1.setSelected(true);
        bg.add(jrb2);


        // 加入各个组件
        jp1.setLayout(new GridLayout(6, 2));
        jp2 = new JPanel();
        jp2.add(jlb1);
        jp2.add(jtf1);
        jp1.add(jp2);

        jp3 = new JPanel();
        jp3.add(jlb2);
        jp3.add(jtf2);
        jp1.add(jp3);

        jp6 = new JPanel();
        jp6.add(jlb3);
        jp6.add(jtf3);
        jp1.add(jp6);

        jp4 = new JPanel();
        jp4.add(jlb4);
        jp4.add(jrb1);
        jp4.add(jrb2);
        jp1.add(jp4);

        jp5 = new JPanel();
        jp5.add(jb1);
        jp5.add(jb3);
        jp5.add(jb2);
        jp1.add(jp5);

        jta1 = new JTextArea("",1,1);

        // 加入到JFrame
        this.setLayout(new GridLayout(1, 2));
        this.add(jp1);
        JScrollPane scroll = new JScrollPane(jta1);
        this.add(scroll);

//        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setSize(d.width, d.height);
        this.setSize(750, 350);
        this.setTitle("计算机");
        this.setLocation(500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /*
     * 年化率有复利
     *
     * @param capital 本金
     * @param annualizedRate 年华率
     * @param day 天数
     *
     * */
    public StringBuffer overlying(double capital, double annualizedRate, int day){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < day; i++) {
            double lixi = (capital*annualizedRate/365);
            sb.append("第"+(i+1)+"日收益："+lixi+"\r\n");
            capital = capital + lixi;
        }
        sb.append("到期收入-->"+capital);
        return sb;
    }

    /*
     * 年化率无复利
     *
     * @param capital 本金
     * @param annualizedRate 年华率
     * @param day 天数
     *
     * */
    public StringBuffer notoverlying(double capital, double annualizedRate, int day){
        StringBuffer sb = new StringBuffer();
        double lixi = capital * annualizedRate;
        sb.append("日收益："+lixi/day+"\r\n");
        capital = capital + lixi;
        sb.append("到期收入-->"+capital);
        return sb;
    }
}
