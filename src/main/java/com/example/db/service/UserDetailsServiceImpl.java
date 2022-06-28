package com.example.db.service;

import com.example.db.entity.Role;
import com.example.db.entity.User;
import com.example.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + username + " not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(
                        user.getUserEmail())
                .password(
                        user.getUserPassword())
                .authorities(
                        getAuthorities(user))
                .build();
    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        Set<Role> userRoles = new HashSet<>(user.getRoles());
        Collection<GrantedAuthority> authorities = new ArrayList<>(userRoles.size());
        for (Role userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return authorities;
    }
}

