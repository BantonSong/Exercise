package com.song.exercise.mvp.bean;

import java.util.List;

/**
 * Java Bean层
 */
public class User {
    private String userName;
    private String password;
    private List<Course> courses;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
