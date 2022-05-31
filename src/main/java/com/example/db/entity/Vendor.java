package com.example.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vendor")
public class Vendor implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_vendor")
    private Integer vendorId;
    @Column(name = "name_vendor")
    private String nameVendor;
    @Column(name = "surname")
    private String surnameVendor;
    @Column(name = "phone",length = 10)
    private String phone;

    @ManyToMany(mappedBy = "vendors")
    private List<Book> bookVendors = new ArrayList<>();

    public Vendor(String nameVendor,String surnameVendor, String phone){
        this.nameVendor=nameVendor;
        this.surnameVendor=surnameVendor;
        this.phone=phone;
    }

}
