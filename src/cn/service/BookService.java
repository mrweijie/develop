package cn.service;

import cn.entity.Book;

import java.util.List;

public interface  BookService {
    /*
     * 获得所有图书类型
     */
    public List<Book> getAllBooks();

    /*
     * 获得单个图书
     */
    public Book getBookById(Integer id);
    /**
     * 添加图书
     */
    public Integer add(Book entity);
    /**
     * 根据图书编号删除图书
     */
    public Integer delete(Integer id);
    /**
     * 更新图书
     */
    public Integer update(Book entity);

}
