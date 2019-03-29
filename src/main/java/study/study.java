package main.java.study;


import main.java.study.aop.IUserDao;
import main.java.study.aop.ProxyFactory;
import main.java.study.aop.UserDao;
import main.java.study.aop.UserDaoProxy;

public class study {
    public static void main(String[] args) {
        IUserDao proxy = new UserDaoProxy();
        proxy.save();

        IUserDao target = new UserDao();
        proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        proxy.save();
    }
}
