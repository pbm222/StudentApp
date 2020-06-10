package com.accenture.StudentApp2.userModel;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor @Getter @Setter
public class Role {

    @Id
    @GeneratedValue
    private  Long id;

    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


}
