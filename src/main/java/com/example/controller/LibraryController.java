package com.example.controller;

import com.example.db.entity.Book;
import com.example.db.entity.Category;
import com.example.db.entity.SubCategory;
import com.example.db.repositories.BookRepository;
import com.example.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;


@Controller
public class LibraryController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/library")
    public String getLibraryPage(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "library";
    }

    @GetMapping("/library/{bookId}")
    public String libraryBook(@PathVariable(value = "bookId") Integer bookId, Model model) {
        Book book = bookRepository.findOne(bookId);
        List<SubCategory> subCategories = book.getSubCategories();
        Category category = subCategories.get(0).getCategory();
        model.addAttribute("book", book);
        model.addAttribute("subCategory", subCategories);
        model.addAttribute("category", category);
        return "book";
    }

    @GetMapping("/library/{bookId}/delete")
    public String libraryBookDelete(@PathVariable(value = "bookId") Integer bookId, Model model) {
        //bookRepository.deleteById(bookId);
        return "book";
    }

    @GetMapping("/add-book")
    public String getAddBook(Model model) {
        return "addBook";
    }

    @PostMapping("/add-book")
    public String addBook(@RequestParam String bookName, @RequestParam Double costBook, @RequestParam Integer numPages, @RequestParam Date produceYear, Model model) {
        Book book = new Book(bookName,costBook,numPages,produceYear);
        //book.addUser(userRepository.findOne(userId));
        bookRepository.save(book);
        return "index";
    }
}
