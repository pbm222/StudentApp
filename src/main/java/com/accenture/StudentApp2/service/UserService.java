package com.accenture.StudentApp2.service;

import com.accenture.StudentApp2.dao.GenericDaoImpl;
import com.accenture.StudentApp2.emailConfirmation.VerificationToken;
import com.accenture.StudentApp2.repository.RoleRepository;
import com.accenture.StudentApp2.repository.UserRepository;
import com.accenture.StudentApp2.userModel.Role;
import com.accenture.StudentApp2.userModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements com.accenture.StudentApp2.service.Service<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void setDao(GenericDaoImpl<User> genericDao) { //TODO:delete this
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.empty();
    }

    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setActive(true);

        Role userRole = roleRepository.findByRole("USER");
        List<Role> roles = roleRepository.findAll();
        //user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


}
