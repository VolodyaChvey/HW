package com.chvey.domain;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true,nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String password;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
