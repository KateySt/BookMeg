package com.example.controller;

import com.example.db.entity.*;
import com.example.db.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.ArrayUtils;

import java.sql.Date;
import java.util.List;


@Controller
public class LibraryController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private AuthorRepository authorRepository;

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
        //List<Category> categories = categoryRepository.findAll();
        //List<SubCategory> subCategories = subCategoryRepository.findAll();
        // List<Author> authors = authorRepository.findAll();
        // model.addAttribute("categories", categories);
        // model.addAttribute("subCategories", subCategories);
        // model.addAttribute("authors", authors);
        return "addBook";
    }

    @PostMapping("/add-book")
    public String addBook(@RequestParam String bookName, @RequestParam Double costBook, @RequestParam Integer numPages, @RequestParam Date produceYear, Model model) {
        Book book = new Book(bookName, costBook, numPages, produceYear);
        User user = userRepository.findByEmail(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName())
                .orElseThrow(() -> new RuntimeException("no find user"));

        if (user.getRoles().size()==1) {
            user.addRole(roleRepository.findOne(3));
            userRepository.update(user);
        }
        book.addUser(user);
        bookRepository.save(book);
        return "index";
    }
}