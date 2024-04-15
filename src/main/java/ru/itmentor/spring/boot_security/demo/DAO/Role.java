package ru.itmentor.spring.boot_security.demo.DAO;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    public Role() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}