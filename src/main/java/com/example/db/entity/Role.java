package com.example.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="role")
public class Role implements Serializable, GrantedAuthority {
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

    @Override
    public String getAuthority() {
        return getRole();
    }
}
