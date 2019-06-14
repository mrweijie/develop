package moneyMatters;

import java.util.Scanner;

public class countMain {
    public static void main(String[] args) {
        countMain countMain = new countMain();
        while (true){
            Scanner sc = new Scanner(System.in);
            int over;
            while (true){
                System.out.println("请选择有无复利： 1、有  0、无");
                over = sc.nextInt();
                if(over == 1 || over == 0){
                    break;
                }
            }
            System.out.println("请输入本金：");
            double capital = sc.nextDouble();
            System.out.println("请输入年化率：");
            double annualizedRate = sc.nextDouble();
            System.out.println("请输入天数：");
            int day = sc.nextInt();
            System.out.println("你的信息如下：");
            if(over == 1){
                System.out.println("到期收入-->"+countMain.overlying(capital,annualizedRate,day));
            }else{
                System.out.println("到期收入-->"+countMain.notoverlying(capital,annualizedRate,day));
            }

            System.out.println("是否继续： 1、继续  0、退出");
            over = sc.nextInt();
            if(over == 0){
                break;
            }
        }
    }

    /*
     * 年化率有复利
     *
     * @param capital 本金
    * @param annualizedRate 年华率
    * @param day 天数
    *
    * */
    public double overlying(double capital, double annualizedRate, int day){
        for (int i = 0; i < day; i++) {
            double lixi = (capital*annualizedRate/365);
            System.out.println("第"+(i+1)+"日收益："+lixi);
            capital = capital + lixi;
        }
        return capital;
    }

    /*
     * 年化率无复利
     *
     * @param capital 本金
     * @param annualizedRate 年华率
     * @param day 天数
     *
     * */
    public double notoverlying(double capital, double annualizedRate, int day){
        double lixi = capital * annualizedRate;
        System.out.println("日收益："+lixi/day);
        capital = capital + lixi;

        return capital;
    }
}
