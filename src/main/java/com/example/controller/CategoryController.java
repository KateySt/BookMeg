package com.example.controller;

import com.example.db.entity.Author;
import com.example.db.entity.Book;
import com.example.db.entity.Category;
import com.example.db.entity.SubCategory;
import com.example.db.repositories.AuthorRepository;
import com.example.db.repositories.CategoryRepository;
import com.example.db.repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/search")
    public String getCategoryPage(Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("subCategories", subCategories);
        model.addAttribute("authors", authors);
        return "search";
    }

    @GetMapping("/category/{categoryId}")
    public String searchByCategoryLibraryBook(@PathVariable(value = "categoryId") Integer categoryId, Model model) {
        Category category = categoryRepository.findOne(categoryId);
        List<SubCategory> subCategories = category.getSubCategories();
        List<Book> books = subCategories.get(0).getBookSubCategories();
        for (int i = 1; i < subCategories.size(); i++) {
            books.addAll(subCategories.get(i).getBookSubCategories());
        }
        Set<Book> bookSet = new HashSet<>(books);
        model.addAttribute("book", bookSet);
        return "searchBookBy";
    }
    @GetMapping("/sub-category/{subCategoryId}")
    public String searchBySubCategoryLibraryBook(@PathVariable(value = "subCategoryId") Integer subCategoryId, Model model) {
        SubCategory subCategories = subCategoryRepository.findOne(subCategoryId);
        List<Book> books = subCategories.getBookSubCategories();
        model.addAttribute("book", books);
        return "searchBookBy";
    }
    @GetMapping("/author/{authorId}")
    public String searchByAuthorLibraryBook(@PathVariable(value = "authorId") Integer authorId, Model model) {
        Author author = authorRepository.findOne(authorId);
        List<Book> books = author.getBookAuthors();
        model.addAttribute("book", books);
        model.addAttribute("authors", author);
        return "searchBookBy";
    }
}