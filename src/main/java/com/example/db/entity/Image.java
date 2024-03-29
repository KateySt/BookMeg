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
@Table(name="image")
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Integer imgId;

    @Column(name = "image")
    private String img;

    @ManyToMany(mappedBy = "images")
    private List<Book> bookImages = new ArrayList<>();

    public Image(String img){
        this.img=img;
    }

    @Override
    public String toString() {
        return  img ;
    }
}
