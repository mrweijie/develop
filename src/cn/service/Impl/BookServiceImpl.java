package cn.service.Impl;

import cn.dao.BookMapper;
import cn.entity.Book;
import cn.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;//mapper的接口

    @Override
    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks();
    }

    @Override
    public Book getBookById(Integer id) {
        return bookMapper.getBookById(id);
    }

    @Override
    public Integer add(Book entity) {
        return bookMapper.add(entity);
    }

    @Override
    public Integer delete(Integer id) {
        return bookMapper.delete(id);
    }

    @Override
    public Integer update(Book entity) {
        return 0;
    }
}