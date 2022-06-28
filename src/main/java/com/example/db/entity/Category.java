package com.example.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="category")
public class Category implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Integer categoryId;

    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<SubCategory> subCategories= new ArrayList<>();

    public Category(String category){
        this.category=category;
    }

    @Transactional
    public void addSubCategory(SubCategory subCategory) {
        subCategories.add(subCategory);
    }

    @Transactional
    public void removeSubCategory(SubCategory subCategory) {
        subCategories.remove(subCategory);
    }

    @Override
    public String toString() {
        return  category;
    }
}
