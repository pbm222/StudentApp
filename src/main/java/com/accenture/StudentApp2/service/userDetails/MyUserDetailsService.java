package com.accenture.StudentApp2.service.userDetails;

import com.accenture.StudentApp2.repository.UserRepository;
import com.accenture.StudentApp2.service.UserService;
import com.accenture.StudentApp2.userModel.Role;
import com.accenture.StudentApp2.userModel.User;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();
        if(user == null){
            throw new UsernameNotFoundException("User nor found:" + username);
        }
        MyUserDetails myUserDetails = new MyUserDetails(user);
        return myUserDetails;
    }

}
