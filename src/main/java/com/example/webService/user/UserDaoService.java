package com.example.webService.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1,"Kenneth",new Date()));
        users.add(new User(2,"Alice",new Date()));
        users.add(new User(3,"Elena",new Date()));
    }

    private static int usersCount = users.size();

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id) throws NullPointerException{
        return users
                .stream()
                .filter((value) -> value.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(String.format("ID[%s] not found" , id)));
    }

    public User save(User user){
        if(user.getId() == null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()){
            User user = iterator.next();

            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }

        return null;
    }



}
