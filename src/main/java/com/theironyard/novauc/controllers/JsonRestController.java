package com.theironyard.novauc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.theironyard.novauc.entities.User;
import com.theironyard.novauc.entities.UserDao;

@RestController
@RequestMapping("/rest/")
public class JsonRestController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public String index() {
        return "<h1>********THIS SITE IS PROTECTED BY THE US GOVERNMENT!********</h1>" +
                "Use the following Commands:<br><br>" +
                "CREATE USER: /rest/createUser?email=[email]&name=[name]<br>" +
                "GET ALL USERS: /rest/getAllUsers<br>" +
                "GET USER BY ID: /rest/getUser?id=[id]<br>" +
                "GET USER BY EMAIL: /rest/getUserByEmail?email=[email]<br>" +
                "UPDATE USER: /rest/updateUser?id=[id]&email=[email]&name=[name]<br>" +
                "DELETE USER: /rest/deleteUser?id=[id]<br><br><br><br>" +
                "<h1>***********LEAVE THIS SITE NOW!********<h1>" +
                "<h1>***********LEAVE THIS SITE NOW!********<h1>" +
                "<h1>***********LEAVE THIS SITE NOW!********<h1>" +
                "<h1>***********LEAVE THIS SITE NOW!********<h1>" +
                "<h1>***********LEAVE THIS SITE NOW!********<h1>" +
                "<h1>***********LEAVE THIS SITE NOW!********<h1>" +
                "<h1>***********LEAVE THIS SITE NOW!********<h1>";

    }


    @RequestMapping("createUser")
    public String create(String username, String address, String email, String ssn, boolean working) {
        try {
            User user = new User(username, address, email, ssn, working);
            userDao.save(user);
            return "User " + user.getId() + " created!";
        } catch (Exception e) {
            return "Error creating user: " + e.toString();
        }
    }

    @RequestMapping("getAllUsers")
    public Iterable<User> getUser() {
        return userDao.findAll();
    }

    @RequestMapping("getUser")
    public User getUser(long id) {
        return userDao.findOne(id);
    }

    @RequestMapping("getUserByEmail")
    public User getByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @RequestMapping("updateUser")
    public String updateUser(long id, String username, String address, String email, String ssn, Boolean working) {
        try {
            User user = userDao.findOne(id);
            user.setUsername(username);
            user.setAddress(address);
            user.setEmail(email);
            user.setSsn(ssn);
            user.setWorking(working);
            userDao.save(user);
            return "User " + id + " updated!";
        } catch (Exception e) {
            return "Error updating user: " + e.toString();
        }
    }

//    public void User(String username, String address, String email, String ssn, boolean working) {
//        this.username = username;
//        this.address = address;
//        this.email = email;
//        this.ssn = ssn;
//        this.working = working;

        @RequestMapping("deleteUser")
        public String deleteUser(long id){
            try {
                userDao.delete(userDao.findOne(id));
                return "User " + id + " deleted!";
            } catch (Exception e) {
                return "Error deleting user:" + e.toString();
            }
        }
    }
