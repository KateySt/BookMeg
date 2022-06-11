package com.example.controller;

import com.example.db.entity.Book;
import com.example.db.entity.Category;
import com.example.db.entity.SubCategory;
import com.example.db.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        //model.addAttribute("book", bookRepository.delete());
        return "book";
    }
}
