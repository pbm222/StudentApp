package com.accenture.StudentApp2.security;

import com.accenture.StudentApp2.repository.UserRepository;
import com.accenture.StudentApp2.service.UserService;

import com.accenture.StudentApp2.userModel.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;


@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

   @Autowired
   private UserService userService;

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private FBService fbService;

    @Override
    public String execute(Connection<?> connection) {
        User user = new User();
        user.setUsername(connection.getDisplayName());
        user.setPassword("1234567890");

        //String accessToken = connection.createData().getAccessToken();
        Facebook facebook = (Facebook) connection.getApi();
        String [] fields = { "id", "email", "name", "firstName", "lastName" };
        org.springframework.social.facebook.api.User userProfile = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);

        user.setEmail(userProfile.getEmail());
        user.setActive(true);
        user.setFirstName(userProfile.getFirstName());
        user.setLastName(userProfile.getLastName());

        userService.save(user);

        //userRepository.save(user);
        return user.getUsername();
    }
}
