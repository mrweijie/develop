package GUI.gupiao;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AppStart {
    public static void main(String[] args) {
        double chengben = 9.00;
        int num = 400;
        AppStart appStart = new AppStart();
        double yongjin = appStart.yongjin(30.96*300);
        System.out.println(yongjin);

    }

    public double yongjin(double money){
        System.out.println(money);
        money = money * 0.0003;
        System.out.println(money);
        BigDecimal bigDecimal = new BigDecimal(money).setScale(2, RoundingMode.HALF_UP);
        double newmoney = bigDecimal.doubleValue();
        if(newmoney < 5){
            return 5;
        }else {
            return newmoney;
        }
    }

    public double guohu(double money){
        money = money * 0.00002;
        BigDecimal bigDecimal = new BigDecimal(money).setScale(2, RoundingMode.HALF_UP);
        double newmoney = bigDecimal.doubleValue();
        if(newmoney < 1){
            return 1;
        }else {
            return newmoney;
        }
    }



    public double yinghua(double money){
        money = money * 0.001;
        BigDecimal bigDecimal = new BigDecimal(money).setScale(2, RoundingMode.HALF_UP);
        double newmoney = bigDecimal.doubleValue();
        return newmoney;
    }
}
