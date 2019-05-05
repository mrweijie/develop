package main.java.study.aop;


import cn.entity.Book;
import main.java.study.aop.IUserDao;
import main.java.study.aop.ProxyFactory;
import main.java.study.aop.UserDao;
import main.java.study.aop.UserDaoProxy;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;
import java.util.stream.Stream;

import static org.apache.commons.collections4.CollectionUtils.filter;

public class study {
    public static void main(String[] args) throws InterruptedException {
//        IUserDao proxy = new UserDaoProxy();
//        proxy.save();
//
//        IUserDao target = new UserDao();
//        proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
//        proxy.save();
//        List<Book> bookList = new ArrayList<Book>();
//        Book book1 = new Book(1,"a",15,new Date());
//        Book book2 = new Book(2,"b",16,new Date());
//        Book book3 = new Book(3,"c",17,new Date());
//        Book book4 = new Book(4,"d",18,new Date());
//        Book book5 = new Book(5,"e",19,new Date());
//
//        bookList.add(book1);
//        bookList.add(book2);
//        bookList.add(book3);
//        bookList.add(book4);
//        bookList.add(book5);
//
//        bookList.stream().filter((book)->book.getPrice()>17).forEach(book->System.out.println(book.getPrice()+1));

//        int a = 50;
//        System.out.println(Integer.toBinaryString(a));
//        System.out.println(Integer.toBinaryString(a>>3));
//        System.out.println(Integer.toBinaryString(a<<3));
//        System.out.println(Integer.toBinaryString(a>>>3));
//
//        System.out.println(Integer.toOctalString(50));

        int a = 60; /* 60 = 0011 1100 */
        int b = 13; /* 13 = 0000 1101 */
        int c = 0;
        c = a & b;       /* 12 = 0000 1100 */
        System.out.println("a & b = " + c );
    }
//    private ApplicationContext ac = new ClassPathXmlApplicationContext("abc.xml",getClass());
//    @Test
//    public void test() throws Exception{
//        IUserDao aaa = (IUserDao) ac.getBean("userDao");
//        System.out.println(aaa.getClass());
//        aaa.save();
//        HashMap a =new HashMap();
//    }
}
