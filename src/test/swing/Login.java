package test.swing;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 1.定义Login类，
 */
public class Login {

    /**
     * 1.在类中定义主函数和
     */
    public static void main(String[] args) {
        // 2.在主函数中实例化Login类的对象，然后用对象名调用初始化界面的方法。
        Login login = new Login();
        login.initUI();
    }

    /**
     * 1.初始化界面的方法。
     */
    public void initUI() {
        // 3.在initUI方法中，实例化JFrame窗体容器组件类的对象。
        JFrame frame = new JFrame();
        // 4.设置窗体容器组件的属性值：标题、大小、显示位置、关闭操作、禁止调整组件大小、布局、可见。
        frame.setTitle("Login");// 设置窗体的标题属性值
        frame.setSize(380, 320);// 设置窗体的大小属性值，单位是像素
        frame.setLocationRelativeTo(null);// 设置窗体显示在屏幕的中央
        frame.setDefaultCloseOperation(3);// 设置窗体的关闭操作，3表示关闭窗体退出程序。
        frame.setResizable(true);// 设置禁止调整窗体的大小

        /**
         * // 实例化FlowLayout流式布局类的对象 FlowLayout fl = new
         * FlowLayout(FlowLayout.CENTER, 5, 5); frame.setLayout(fl);//
         * 设置窗体的布局方式为流式布局
         */

        BorderLayout bl = new BorderLayout();// 实例化BorderLayout边框 布局类的对象
        frame.setLayout(bl);// JFrame窗体容器组件默认的布局方式就是BorderLayout边框布局

        // 5.实例化元素组件类的对象，添加到窗体上(组件的添加要在窗体可见之前完成)。

//        // 实例化ImageIcon图标类的对象，该对象加载磁盘上的图片到内存中，组件就可以显示ImageIcon的对象
//        ImageIcon icon = new ImageIcon("这里放图片地址");
//        // 实例化JLabel标签元素组件类的对象，组件显示icon图标对象
//        JLabel labIcon = new JLabel(icon);
//        // 将labIcon添加到窗体的北边
//        frame.add(labIcon, BorderLayout.NORTH);

        // 实例化JLabel标签元素组件类的对象，组件显示"账号："文字
        JLabel labName = new JLabel("账号：");
        // 将labName添加到窗体上
        frame.add(labName, BorderLayout.BEFORE_FIRST_LINE);// 在边框布局中，将组件添加到窗体的西边

//        // 实例化JLabel标签元素组件类的对象，组件显示"密码："文字
//        JLabel labPassword = new JLabel("密码：");
//        // 将labPassword添加到窗体上
//        frame.add(labPassword, BorderLayout.SOUTH);// 在边框布局中，将组件添加到窗体的南边
//
//        // 实例化JButton标签元素组件类的对象，组件显示"东边："文字
//        JButton labeast = new JButton("东边：");
//        // 将labeast添加到窗体上
//        frame.add(labeast, BorderLayout.EAST);// 在边框布局中，将组件添加到窗体的东边
//
//        //实例化JPanel面板容器组件类的对象
//        JPanel centerPane = new JPanel();
//        centerPane.setLayout(new FlowLayout());
//
//
//        JTextField textName = new JTextField();
//        textName.setPreferredSize(new Dimension(180,30));//此方法不能用于JFrame。
//        centerPane.add(textName);
//
//        String [] array = {"257037851"};
//        JComboBox  cbItem = new JComboBox(array);
//        cbItem.setPreferredSize(new Dimension(180,30));
//        centerPane.add(cbItem);

//        frame.add(centerPane);

        frame.setVisible(true);// 设置窗体为可见，这个是必须写的，且必须在最后，否则会看不到一些组件
    }

}