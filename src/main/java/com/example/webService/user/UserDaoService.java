package com.example.webService.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = users.size();

    static {
        users.add(new User(1,"Kenneth",new Date()));
        users.add(new User(2,"Alice",new Date()));
        users.add(new User(3,"Elena",new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id){
        return users.stream().filter((value) -> value.getId().equals(id)).findFirst().orElse(null);
    }

    public User save(User user){
        if(user.getId() == null){
            user.setId(usersCount);
        }
        users.add(user);
        return user;
    }

}
