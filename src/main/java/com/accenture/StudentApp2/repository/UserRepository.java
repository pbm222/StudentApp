package com.accenture.StudentApp2.repository;

import com.accenture.StudentApp2.userModel.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUsername(String username);
   Optional<User> findByEmail(String email);
   List<User> findAll();
   User findByEmailIgnoreCase(String email);

}
