package com.spring.security.services;

import com.spring.security.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> list = new ArrayList<>();

    public UserService() {
        list.add(new User("test", "test", "test@gmail.com","user"));
        list.add(new User("test1", "test1", "test1@gmail.com","user"));

        list.add(new User("test2", "test2", "test2@gmail.com","user"));

    }

    public List<User> getAllUsers() {
        return this.list;
    }

    public User getUser(String username) {
        return this.list.stream().filter((user) -> user.getUsername().equals(username)).findAny().orElse(null);

    }
    public User addUser(User user){
        this.list.add(user);
        return user;
    }
}
