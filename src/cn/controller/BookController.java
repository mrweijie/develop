package cn.controller;

import cn.entity.Book;
import cn.service.BookService;
import cn.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/book")
public class BookController {
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
        return "redirect:/";
    }
}
