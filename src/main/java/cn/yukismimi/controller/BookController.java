package cn.yukismimi.controller;

import cn.yukismimi.model.Book;
import cn.yukismimi.service.IBookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {

    @Resource
    private IBookService bookService;

    @RequestMapping("/findBook")
    @ResponseBody
    public Book findBook(@RequestBody Map<String,String> map){
        return bookService.findBook(Integer.parseInt(map.get("id")));
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @RequestMapping("/updateBook")
    @ResponseBody
    public List<Book> updateBook(@RequestBody Map<String,String> map){
        Book book = new Book();
        book.setId(Integer.parseInt(map.get("id")));
        book.setName(map.get("name"));
        book.setAuthor(map.get("author"));
        book.setPrice(Float.parseFloat(map.get("price")));
        bookService.updateBook(book);
        return findAll();
    }

    @RequestMapping("/addBook")
    @ResponseBody
    public List<Book> addBook(@RequestBody Map<String,String> map){
        Book book = new Book();
        book.setId(Integer.parseInt(map.get("id")));
        book.setName(map.get("name"));
        book.setAuthor(map.get("author"));
        book.setPrice(Float.parseFloat(map.get("price")));
        bookService.addBook(book);
        return findAll();
    }

    @RequestMapping("/deleteBook")
    @ResponseBody
    public List<Book> deleteBook(@RequestBody Map<String,String> map){
        bookService.deleteBook(Integer.parseInt(map.get("id")));
        return findAll();
    }

    @RequestMapping("/test")
    public void test(){
        System.out.println("Test");
    }
}
