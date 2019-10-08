package cn.controller;
import cn.entity.Backentity;
import cn.entity.Book;
import cn.service.BookService;
import cn.util.Tools;
import stu.java.tcp.TCPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zwj
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/book")
public class BookController {

    private static volatile Map<String, TCPClient> abstractClientMap = new HashMap<>();
    @Autowired
    BookService bookService;

    @ResponseBody
    @RequestMapping(value="/getAll",method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public String getAll() {
        List<Book> bookList = bookService.getAllBooks();
        return Tools.toJson(bookList);
    }

    @RequestMapping(value = "/add",method = {RequestMethod.POST})
    public String add(HttpServletRequest req){
        Book book = new Book();
        book.setPrice(Double.parseDouble(req.getParameter("price")));
        book.setTitle(req.getParameter("title"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            book.setPublishDate(sdf.parse(req.getParameter("publishDate")));
        } catch (ParseException e) {
            System.out.println("日期装换错误");
            e.printStackTrace();
        }
        bookService.add(book);
        return "redirect:/home/test";
    }

    @RequestMapping(value = "/client",method = {RequestMethod.GET})
    public void client(HttpServletRequest req){
        String ip = "192.168.10.195";
        TCPClient client;
        if(abstractClientMap.get(ip) != null){
            client = abstractClientMap.get(ip);
            client.sendString("222222");
        }else{
            System.out.println("进入");
            client =new TCPClient(ip,50003);
            client.connect();
            client.sendString("111111111");
            abstractClientMap.put(ip,client);
        }
    }

    @ResponseBody
    @RequestMapping(value="/getList",method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public String getList() {
        Backentity backentity = new Backentity();
        backentity.setCode(200);
        backentity.setMassage("OK");
        List<Book> bookList = bookService.getAllBooks();
        backentity.setData(bookList);
        return Tools.toJson(backentity);
    }
}
