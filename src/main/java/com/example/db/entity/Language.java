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
@Table(name="language")
public class Language implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_lang")
    private Integer languageId;
    @Column(name = "language")
    private String language;

    @ManyToMany(mappedBy = "languages")
    private List<Book> bookLanguages = new ArrayList<>();

    public Language(String language){
        this.language=language;
    }

    @Override
    public String toString() {
        return language;
    }
}
