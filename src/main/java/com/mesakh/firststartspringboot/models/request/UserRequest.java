package com.mesakh.firststartspringboot.models.request;

import com.mesakh.firststartspringboot.models.Position;
import com.mesakh.firststartspringboot.models.response.KeyValueItem;
import jakarta.persistence.Transient;

import java.util.List;

public class UserRequest {
    public int id;
    private String username;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;
    private int positionId;
    private String status;
    @Transient
    private List<Position> positionList;
    @Transient
    private List<KeyValueItem> statusList;
    @Transient
    private List<KeyValueItem> roleList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    public List<KeyValueItem> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<KeyValueItem> statusList) {
        this.statusList = statusList;
    }

    public List<KeyValueItem> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<KeyValueItem> roleList) {
        this.roleList = roleList;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
