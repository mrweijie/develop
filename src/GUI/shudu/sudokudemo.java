package GUI.shudu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sudokudemo extends JFrame {

    static long start;
    static int i;

    // 定义组件
    JPanel jpLeft = new JPanel();
    JPanel jpLeftUp = new JPanel();
    JPanel jpLeftDown = new JPanel();

    JTextArea sendText = new JTextArea("",10,25);
    public static JTextArea showText = new JTextArea("",1,1);

    JButton sendButton = new JButton("计算");

    public static String host;
    public static int port;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        start = System.nanoTime();
        sudokudemo d1 = new sudokudemo();


    }

    // 构造函数
    public sudokudemo() {
        jpLeftDown.add(sendText);
        jpLeftDown.add(sendButton);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i = 0;
                showText.setText("");
                String source = sendText.getText();
                source = source.replace("\n", "");
                System.out.println(source.length());
                int[][] data = init(source);
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (data[i][j] == 0) {
                            data[i][j] = 0x1ff;
                        }
                    }
                }
                print(data);
                solve(data);
            }
        });

        jpLeft.add(jpLeftUp);
        jpLeft.add(jpLeftDown);

        this.setLayout(new GridLayout(1, 2));
        this.add(jpLeft);
        this.add(showText);
        JScrollPane scroll = new JScrollPane(showText);
        this.add(scroll);

        this.setSize(1000, 550);
        this.setTitle("数独计算");
        this.setLocation(400,250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * 打印数独
     *
     * @param data
     */
    public static void print(int[][] data) {
        for (int m = 0; m < 9; m++) {
            for (int n = 0; n < 9; n++) {
                int v = getV9(data[m][n]);
                if (v != -1) {
                    showText.append(v + " ");
                } else {
                    showText.append("_ ");
                }
            }
            showText.append("\r\n");
        }
    }


    public static void solve(int[][] data) {
        analyse(data);
        int result = check(data);
        if (result == 1) {
            int[] position = smallPosition(data);
            int pv = data[position[0]][position[1]];
            int pvcount = Integer.bitCount(pv);
            for (int i = 0; i < pvcount; i++) {
                int testV = 1 << ((int) (Math.log(Integer.highestOneBit(pv)) / Math.log(2)));
                pv ^= testV;
//               System.out.println("试填["+position[0]+","+position[1]+"]"+((int)
//               (Math.log(Integer.highestOneBit(testV)) / Math.log(2))+1));
                int[][] copy = copyData(data);
                copy[position[0]][position[1]] = testV;
                solve(copy);
            }
        } else if (result == 0) {
            showText.append("------------------------------------第"+(++i)+"个答案---------------------"
                    + (System.nanoTime() - start) / 1000000.0 + "ms---\r\n");
            print(data);
        }
    }

    /**
     * 复制数独数组
     * @param data
     * @return
     */
    public static int[][] copyData(int[][] data) {
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copy[i][j] = data[i][j];
            }
        }
        return copy;
    }

    /**
     * 找到候选数最少的位置开始尝试<br>
     * <b>****显著提升了效率*****<b>
     * @param data
     * @return int[2] 行列位置
     */
    public static int[] smallPosition(int[][] data) {
        int[] res = null;
        int smallCount = 10;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int bitcount = Integer.bitCount(data[i][j]);
                if (bitcount == 2) {
                    return new int[] { i, j };
                } else if (bitcount != 1) {
                    if (smallCount > bitcount) {
                        smallCount = bitcount;
                        res = new int[] { i, j };
                    }
                }
            }
        }
        return res;
    }

    /**
     * 检查结果
     * @param data
     * @return <b>0</b>   正确<br>
     *                  <b>1</b>    还有位置未填<br>
     *                  <b>-1</b>    错误<br>
     */
    public static int check(int[][] data) {
        for (int i = 0; i < 9; i++) {
            int row = 0;
            int col = 0;
            int block = 0;
            for (int j = 0; j < 9; j++) {
                if (Integer.bitCount(data[i][j]) > 1) {
                    return 1;
                }
                row |= data[i][j];
                col |= data[j][i];
            }

            for (int h = i / 3 * 3; h < i / 3 * 3 + 3; h++) {
                for (int l = i % 3 * 3; l < i % 3 * 3 + 3; l++) {
                    block |= data[h][l];
                }
            }
            if (row != 0x1ff || col != 0x1ff || block != 0x1ff) {
                return -1;
            }
        }
        return 0;
    }

    public static void analyse(int[][] data) {
        boolean changed = false;
        changed = reduce(data);
        //TODO 还可以加入其它删减候选数算法，将这变成一个解题演算器
        if (changed) {
            analyse(data);
        }
    }

    /**
     * 根据行列宫已有值进行简单的候选数删减
     * @param data
     * @return
     */
    public static boolean reduce(int[][] data) {
        boolean changed = false;
        for (int m = 0; m < 9; m++) {
            for (int n = 0; n < 9; n++) {
                if (Integer.bitCount(data[m][n]) != 1) {
                    if (setMaybe(data, m, n)) {
                        changed = true;
                    }
                }
            }
        }
        return changed;
    }

    /**
     * 设置m行n列位置的可能值
     *
     * @param m
     *            行（0-8）
     * @param n
     *            列（0-8）
     * @return 是否减少了候选数
     */
    public static boolean setMaybe(int[][] data, int m, int n) {
        if (Integer.bitCount(data[m][n]) == 1) {
            return false;
        }
        int row = 0;// 行已确定值集合
        int col = 0;// 列已确定值集合
        int block = 0; // 宫格已确定值集合

        for (int i = 0; i < 9; i++) {
            if (Integer.bitCount(data[m][i]) == 1) {
                row += data[m][i];
            }
            if (Integer.bitCount(data[i][n]) == 1) {
                col += data[i][n];
            }
        }

        for (int i = m / 3 * 3; i < m / 3 * 3 + 3; i++) {
            for (int j = n / 3 * 3; j < n / 3 * 3 + 3; j++) {
                if (Integer.bitCount(data[i][j]) == 1) {
                    block += data[i][j];
                }
            }
        }

        int have = row | col | block;// 不可能的值
        int left = 0x1ff ^ have;// 候选数
        // System.out.println("["+m+","+n+"]"+Integer.toBinaryString(left));
        return tryReduce(data, m, n, left);
    }

    /**
     * 新的候选数与老的候选数比较，尝试减少候选数
     *
     * @param data
     * @param m
     *            行（0-8）
     * @param n
     *            列（0-8）
     * @param v
     *            候选数，如0x1F9(二进制1 1111 1001)表示候选数为1 4 5 6 7 8 9
     * @return 是否改变了m行n列的候选数,<br/>
     *         <b>true</b> 减少了候选数<br/>
     *         <b>false</b> 没有减少
     */
    public static boolean tryReduce(int[][] data, int m, int n, int v) {
        int old = data[m][n];
        data[m][n] = old & v;
        return data[m][n] != old;
    }

    /**
     * 初始化数据
     *
     * @param source
     *            空格分开的81个数字 0 表示待填
     * @return
     */
    public static int[][] init(String source) {
        source = source.replace(" ", "");
        int[][] data = new int[9][9];
        for (int i = 0; i < source.length(); i++) {
            //应该用source.charAt(i) - '0'
            int v = Integer.parseInt(source.charAt(i) + "");
            if (v != 0) {
                data[i / 9][i % 9] = 1 << (v - 1);
            }
        }
        return data;
    }

    /**
     * 将使用1的位移表示的数字转换为整数;
     *
     * @param v
     *            用1的位移表示的数值
     * @return
     */
    public static int getV9(int v) {
        // 使用switch与使用Math.log时间效率差不多
        switch (v) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 4:
                return 3;
            case 8:
                return 4;
            case 16:
                return 5;
            case 32:
                return 6;
            case 64:
                return 7;
            case 128:
                return 8;
            case 256:
                return 9;
            default:
                break;
        }
        return -1;
    }

}

