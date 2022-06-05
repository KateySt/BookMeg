package com.example.db.service;

import com.example.db.entity.Role;
import com.example.db.entity.User;
import com.example.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        List<User> userList = userRepository.findByName(username);
        if(userList == null || userList.isEmpty()){
            throw new UsernameNotFoundException("No user Found with username : " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                userList.get(userList.size()-1).getNameUser(),
                userList.get(userList.size()-1).getUserPassword(),
                getAuthorities(userList.get(userList.size()-1))
        );
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles()
                .stream()
                .map(Role::getRole)
                .toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}