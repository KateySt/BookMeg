package com.example.db.service;

import com.example.db.entity.User;
import com.example.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email " + username + " not found"));
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUserEmail()).password(user.getUserPassword()).authorities("USER").build();
        return userDetails;
    }
}
