package com.example.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer roleId;
    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "roles")
    private List<User> roleUsers = new ArrayList<>();

    public Role(String role){
        this.role=role;
    }
}
