package com.example.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "author")
public class Author implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_author")
    private Integer authorId;
    @Column(name = "name_author")
    private String nameAuthor;
    @Column(name = "surname_author")
    private String surnameAuthor;

    @ManyToMany(mappedBy = "authors")
    private List<Book> bookAuthors = new ArrayList<>();

    public Author(String nameAuthor, String surnameAuthor) {
        this.nameAuthor=nameAuthor;
        this.surnameAuthor=surnameAuthor;
    }
    @Override
    public String toString() {
        return  nameAuthor +" "+ surnameAuthor;
    }
}
