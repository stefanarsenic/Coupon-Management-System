package org.example.authservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean tokenExpired;

    @ManyToMany
    @JoinTable(name="user_role",
            joinColumns=
            @JoinColumn(name="user_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="role_id", referencedColumnName="id")
    )
    private List<Role> roles;
}
