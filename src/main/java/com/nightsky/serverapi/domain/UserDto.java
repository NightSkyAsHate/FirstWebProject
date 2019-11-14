package com.nightsky.serverapi.domain;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Set;

@JsonView(Views.UserInfo.class)
public class UserDto {
    private Long id;
    private String username;
    private String phoneNumber;
    private Set<Role> roles;

    public UserDto(Long id, String username, String phoneNumber, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
