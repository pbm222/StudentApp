package com.accenture.StudentApp2.userModel;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Users")
@NoArgsConstructor @Getter @Setter
public class User {

    @Id @GeneratedValue
    private Long id;

    //@NotNull @NotEmpty
    private String username;
    //@NotNull @NotEmpty
    private String password;
    private String firstName;
    private String lastName;
    //@NotEmpty @NotNull
    private String email;
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;




}
