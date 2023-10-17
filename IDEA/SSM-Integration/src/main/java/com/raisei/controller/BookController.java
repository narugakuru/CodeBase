package com.raisei.controller;

import com.raisei.pojo.Books;
import com.raisei.service.BookService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    //    书籍信息展示
    @RequestMapping("/allBook")
    public String allBook(Model model) {
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list", list);
        return "allBook";
    }

    //    接受添加请求，转发
    @RequestMapping("/toAddBook")
    public String toAddBook() {
        return "addBook";
    }

    //    执行添加操作，重定向回首页
    @RequestMapping("/doAddBook")
    public String doAddBook(Books books) {
        System.out.println(books);
        int i = bookService.addBook(books);
        System.out.println("ok===========");
        if (i > 0) {
            System.out.println("添加成功");
        }
        return "redirect:/book/allBook";
    }

    //    执行删除书籍操作,重定向首页
    @RequestMapping("/doDeleteBook")
    public String deleteBook(int id) {
        System.out.println("deleteBook=>id=>" + id);
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

//    修改转发页面，获取需要修改的书的信息
    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(int id, Model model) {
        System.out.println("toUpdateBook=>id:"+id);
        Books books = bookService.queryBookById(id);
        model.addAttribute("books", books);
        return "updateBook";
    }

    //    执行修改操作,重定向首页
    @RequestMapping("/doUpdateBook")
    public String doUpdateBook(Books books) {
        System.out.println("doUpdateBook=>" + books);
        int i = bookService.updateBook(books);
        if (i > 0) {
            System.out.println("修改成功");
        }
        return "redirect:/book/allBook";
    }

}
