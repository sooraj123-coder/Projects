package com.sooraj.Shop2Day.shopsite.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="fname")
    private String fname;

    @Column(name="lname")
    private String lname;

    @Column(name="password")
    private String password;

    @Column(name="active")
    private Integer active;

    @Column(name="email")
    private String email;

    @Column(name="mobilenumber")
    private Long mobnum;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    private List<Role> theRole;


    //adding convinience method for Roles
    public void addRole(Role role){
        if(theRole==null){
            theRole=new ArrayList<>();
        }
        theRole.add(role);
    }

}
