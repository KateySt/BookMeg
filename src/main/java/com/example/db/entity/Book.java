package com.example.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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
    @JoinTable(name = "book_vendor",
            joinColumns = { @JoinColumn(name = "id_book") },
            inverseJoinColumns = { @JoinColumn(name = "id_vendor") })
    private List<Vendor> vendors = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "book_sub_category",
            joinColumns = { @JoinColumn(name = "id_book") },
            inverseJoinColumns = { @JoinColumn(name = "id_sub_category") })
    private List<SubCategory> subAndCategories = new ArrayList<>();

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
    public void addSubAndCategory(SubCategory subCategory) {
        subAndCategories.add(subCategory);
        subCategory.getBookSubAndCategories().add(this);
    }

    @Transactional
    public void removeSubAndCategory(SubCategory subCategory) {
        subAndCategories.remove(subCategory);
        subCategory.getBookSubAndCategories().remove(this);
    }
    @Transactional
    public void addVendor(Vendor vendor) {
        vendors.add(vendor);
        vendor.getBookVendors().add(this);
    }

    @Transactional
    public void removeVendor(Vendor vendor) {
        vendors.remove(vendor);
        vendor.getBookVendors().remove(this);
    }
}
