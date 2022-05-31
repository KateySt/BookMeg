package com.example.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer roleId;
    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy="role")
    private List<User> comments = new ArrayList<>();

    public Role(String role){
        this.role=role;
    }
}
