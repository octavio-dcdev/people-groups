package com.theironyard.novauc.controllers;

import com.theironyard.novauc.entities.User;
import com.theironyard.novauc.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.server.ExportException;
import java.util.List;



@Controller
//@RequestMapping("/product")
//@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
public class PeopleGroupsController {

    @Autowired
    UserRepository users;

    @RequestMapping(value="/")
    public String jspIndex() {
        return "index.html";
    }

    @RequestMapping(value="/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        request.setAttribute("logout","logout");
        return "login";
    }


//    @RequestMapping(path = "/user", method = RequestMethod.GET)
//    public List<User> readUser() {
//        return (List<User>) users.findAll();
//    }
//    @RequestMapping(path = "/user", method = RequestMethod.POST)
//    public void createUser(@RequestBody User user) {
//        users.save(user);
//    }
//    @RequestMapping(path = "/user", method = RequestMethod.PUT)
//    public void updateUser(@RequestBody User user) throws Exception{
//
//        if (users.exists(user.getId())){
//            users.save(user);
//        } else {
//            throw new ExportException("User not found. Check user ID.");
//        }
//
//    }
//    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
//    public void deleteUser(@PathVariable("id") int id) {
//        users.delete(id);
//    }
//
//    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
//    public User getUser(@PathVariable("id") int id) {
//        return users.findOne(id);
//    }
//
//    @RequestMapping(value="/login")
//    public String login() {
//        return "login";
//    }
//
//    @RequestMapping(value="/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        request.setAttribute("logout","logout");
//        return "login";
//    }


    @PostConstruct
    public void init(){
        if (users.count() == 0){
            User user = new User();
            user.setAddress("123 Testville");
            user.setEmail("me@you.com");
            user.setUsername("Mike");
            user.setSsn("333-444-5522");
            user.setWorking(true);
            users.save(user);
        }
    }
}
