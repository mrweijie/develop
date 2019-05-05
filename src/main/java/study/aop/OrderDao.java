package main.java.study.aop;

// 用于测试 CGLIB 动态代理
class OrderDao {
    public void save() {
        //int i =1/0; 用于测试异常通知
        System.out.println("保存订单...");
    }
}