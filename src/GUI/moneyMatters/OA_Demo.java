package GUI.moneyMatters;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OA_Demo extends JFrame {

    // 定义组件
    JPanel jp1;
    JTextArea jta1;
    JTable jt ,jt2;
    JButton b_1 ,b_2;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        OA_Demo d1 = new OA_Demo();
    }

    // 构造函数
    public OA_Demo() {

        jp1 = new JPanel();

        String[] columnNames = new String[] { "id", "name", "hp", "damage" };
        String[] columnNames2 = new String[] { "i2d", "n2ame", "h2p", "dam2age" };
        String[][] heros = new String[][] { { "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" }, { "3", "奎因", "832", "200" },{ "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" }, { "3", "奎因", "832", "200" },{ "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" }, { "3", "奎因", "832", "200" },{ "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" }, { "3", "奎因", "832", "200" },{ "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" }, { "3", "奎因", "832", "200" },{ "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" }, { "3", "奎因", "832", "200" },{ "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" }, { "3", "奎因", "832", "200" },{ "1", "盖伦", "616", "100" },
                { "2", "提莫", "512", "102" }, { "3", "奎因", "832", "200" } };
        jt = new JTable(heros,columnNames2);
        jt2 = new JTable(heros,columnNames);
        jt.setRowHeight(50);
        jt2.setRowHeight(50);

        TableColumn tableColumn = new TableColumn();

        JScrollPane sp = new JScrollPane(jt);
        JScrollPane sp2 = new JScrollPane(jt2);

        b_1 = new JButton("1111");
        b_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sp.setVisible(false);
            }
        });
        b_2 = new JButton("222");
        jp1.add(b_1);
        jp1.add(b_2);

        // 加入到JFrame
//        this.setLayout(new GridLayout(1, 2));
        this.setLayout(new BorderLayout());
        this.add(jp1,BorderLayout.WEST);
        this.add(sp2,BorderLayout.EAST);
        this.add(sp,BorderLayout.EAST);

//        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setSize(d.width, d.height);
        this.setSize(1500, 800);
        this.setTitle("计算机");
        this.setLocation(10,10);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
