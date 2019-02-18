package cn.controller;

import cn.entity.Book;
import cn.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/book")
public class BookController {
    @Resource
    BookService bookService;

    @RequestMapping(value="/getAll")
    public void getAll() {
        List<Book> bookList = bookService.getAllBooks();
        System.out.println(bookList);
    }
}
