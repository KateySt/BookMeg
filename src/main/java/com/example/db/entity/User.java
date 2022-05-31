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
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer userId;
    @Column(name = "user_name")
    private String nameUser;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "user_phone")
    private String userPhone;
    @Column(name = "active")
    private Boolean active;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_role")
     private Role role;


    @ManyToMany(mappedBy = "users")
    private List<Book> bookUsers = new ArrayList<>();

    public User(String nameUser,String userEmail, String userPhone,String userPassword) {
        this.nameUser=nameUser;
        this.userEmail=userEmail;
        this.userPhone=userPhone;
        this.userPassword=userPassword;
    }

    public User(String nameUser, String userPassword) {
        this.nameUser=nameUser;
        this.userPassword=userPassword;
    }
}
