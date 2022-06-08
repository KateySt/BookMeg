package com.example.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "sub_category")
public class SubCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sub_category")
    private Integer subCategoryId;
    @Column(name = "sub_category")
    private String subCategory;

    @ManyToMany(mappedBy = "subCategories")
    private List<Book> bookSubCategories = new ArrayList<>();

    public SubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category")
    private Category category;

    @Override
    public String toString() {
        return  subCategory;
    }
}
