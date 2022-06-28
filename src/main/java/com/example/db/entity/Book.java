package com.example.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="book")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Integer bookId;
    @Column(name = "name_book")
    private String bookName;
    @Column(name = "cost_book")
    private Double costBook;
    @Column(name = "num_pages")
    private Integer numPages;
    @Column(name = "produce_year")
    private Date produceYear;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "author_books",
            joinColumns = { @JoinColumn(name = "id_book") },
            inverseJoinColumns = { @JoinColumn(name = "id_author") })
    private List<Author> authors = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "book_language",
            joinColumns = { @JoinColumn(name = "id_book") },
            inverseJoinColumns = { @JoinColumn(name = "id_language") })
    private List<Language> languages = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "book_sub_category",
            joinColumns = { @JoinColumn(name = "id_book") },
            inverseJoinColumns = { @JoinColumn(name = "id_sub_category") })
    private List<SubCategory> subCategories = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "book_image",
            joinColumns = { @JoinColumn(name = "id_book") },
            inverseJoinColumns = { @JoinColumn(name = "id_image") })
    private List<Image> images = new ArrayList<>();

    public Book(String bookName, double costBook, int numPages,Date produceYear) {
        this.bookName=bookName;
        this.costBook=costBook;
        this.numPages=numPages;
        this.produceYear=produceYear;
    }
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "book_user",
            joinColumns = { @JoinColumn(name = "id_book") },
            inverseJoinColumns = { @JoinColumn(name = "id_user") })
    private List<User> users = new ArrayList<>();

    @Transactional
    public void addUser(User user) {
        users.add(user);
        user.getBookUsers().add(this);
    }

    @Transactional
    public void removeUser(User user) {
        users.remove(user);
        user.getBookUsers().remove(this);
    }

    @Transactional
    public void addAuthor(Author author) {
        authors.add(author);
        author.getBookAuthors().add(this);
    }

    @Transactional
    public void removeAuthor(Author author) {
        authors.remove(author);
        author.getBookAuthors().remove(this);
    }

    @Transactional
    public void addLanguage(Language language) {
        languages.add(language);
        language.getBookLanguages().add(this);
    }

    @Transactional
    public void removeLanguage(Language language) {
        languages.remove(language);
        language.getBookLanguages().remove(this);
    }

    @Transactional
    public void addImage(Image image) {
        images.add(image);
        image.getBookImages().add(this);
    }

    @Transactional
    public void removeImage(Image image) {
        images.remove(image);
        image.getBookImages().remove(this);
    }
    @Transactional
    public void addSubCategory(SubCategory subCategory) {
        subCategories.add(subCategory);
        subCategory.getBookSubCategories().add(this);
    }

    @Transactional
    public void removeSubCategory(SubCategory subCategory) {
        subCategories.remove(subCategory);
        subCategory.getBookSubCategories().remove(this);
    }
}
