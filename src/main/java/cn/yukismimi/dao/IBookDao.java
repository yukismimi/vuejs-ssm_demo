package cn.yukismimi.dao;

import cn.yukismimi.model.Book;

import java.util.List;

public interface IBookDao {

    public Book findBook(int id);

    public List<Book> findAll();

    public void updateBook(Book book);

    public void addBook(Book book);

    public void deleteBook(int id);
}
