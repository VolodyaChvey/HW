package com.chvey.domain;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @OneToMany(mappedBy = "user_id")
    private int id;
    @Column(nullable = false)
    private String name;
    @Column
    private String password;

    public User(int id, String name) {
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
