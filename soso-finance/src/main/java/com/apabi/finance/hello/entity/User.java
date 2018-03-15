package com.apabi.finance.hello.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by acer_liuyutong on 2017/5/10.
 */
@Entity
@Table(name = "`user`")
public class User implements Serializable{
    /**
     * 主键ID
     */
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;
    /**
     *
     */
    private String username;
    /**
     *
     */
    private String password;
    /**
     *
     */
    private Integer age;
    /**
     *
     */
    private String email;
    /**
     *
     */
    private Date createDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}