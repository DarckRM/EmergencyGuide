package com.emergencyguide.Entity;

import java.io.Serializable;

/**
 * @author DarckLH
 * @date 2021/5/11 1:20
 * @Description
 */
public class Role implements Serializable {

    private int id;
    private String role;
    private int authority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", authority=" + authority +
                '}';
    }
}
