package main.java.study.aop;

//目标对象
public class UserDao implements IUserDao{
    @Override
    public void save() {
        System.out.println("模拟：保存用户！");
    }
}
