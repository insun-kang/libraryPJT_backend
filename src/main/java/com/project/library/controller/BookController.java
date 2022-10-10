package com.project.library.controller;

import com.project.library.domain.Book;
import com.project.library.repository.BookRepository;
import com.project.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @ResponseBody
    @GetMapping("")
    public List<Book> books(){
        List<Book> books = bookService.findBooks();
        return books;
    }

    @ResponseBody
    @PostMapping("/search")
    public Book search(@RequestBody Long bookId){
        Book book = bookService.findOne(bookId);
        return book;
    }

    @ResponseBody
    @PostMapping("/regist")
    public void regist(@RequestBody Book book){
        bookService.saveBook(book);
    }

    @ResponseBody
    @PostMapping("/edit")
    public void edit(@RequestBody Book book){
        bookService.saveBook(book);
    }

    @ResponseBody
    @PostMapping("/delete")
    public void delete(@RequestBody Book book){
        bookService.saveBook(book);
    }

}
