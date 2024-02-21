package com.learnrestapi.restfulapi.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int id = 0;
    static {
        users.add(new User(++id, "Jitendra Kumar Pandey",
                LocalDate.now().minusYears(30)));

        users.add(new User(++id, "Yogendra Pandey",
                LocalDate.now().minusYears(20)));

        users.add(new User(++id, "Sudeshna Singh",
                LocalDate.now().minusYears(35)));

        users.add(new User(++id, "Anil Kapoor",
                LocalDate.now().minusYears(40)));
    }

    public List<User> findAll(){
        return users;
    }

    public User getUserById(int id){
        User user = users.stream().filter(u-> u.getId() == id).findFirst().orElse(null);
        return user;
    }

    public void save(User user){
        user.setId(++id);
        users.add(user);
    }


    public void deleteById(int id){
        users.removeIf(user-> user.getId()==id);
    }
}
