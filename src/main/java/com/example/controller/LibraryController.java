package com.example.controller;

import com.example.db.entity.Book;
import com.example.db.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LibraryController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/library")
    public String getLibraryPage(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "library";
    }

    @GetMapping("/library/{bookId}")
    public String libraryBook(@PathVariable(value = "bookId") Integer bookId, Model model) {
        model.addAttribute("book", bookRepository.findOne(bookId));
        return "book";
    }

    @GetMapping("/library/{bookId}/delete")
    public String libraryBookDelete(@PathVariable(value = "bookId") Integer bookId, Model model) {
        //model.addAttribute("book", bookRepository.delete());
        return "book";
    }
}
