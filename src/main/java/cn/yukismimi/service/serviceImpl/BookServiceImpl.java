package cn.yukismimi.service.serviceImpl;

import cn.yukismimi.dao.IBookDao;
import cn.yukismimi.model.Book;
import cn.yukismimi.service.IBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    @Resource
    private IBookDao bookDao;

    public Book findBook(int id){
        return bookDao.findBook(id);
    }

    public List<Book> findAll(){
        return bookDao.findAll();
    }

    public void updateBook(Book book){
        bookDao.updateBook(book);
    }

    public void addBook(Book book){
        bookDao.addBook(book);
    }

    public void deleteBook(int id){
        bookDao.deleteBook(id);
    }
}
