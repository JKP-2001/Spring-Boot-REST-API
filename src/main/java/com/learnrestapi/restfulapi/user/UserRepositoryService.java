package com.learnrestapi.restfulapi.user;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryService {

    private UserRepository userRepository;


    public UserRepositoryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        User user = userRepository.findById(id).orElse(null);

//        User user = users.stream().filter(u-> u.getId() == id).
//                findFirst().orElse(null);
        return user;
    }

    public void save(User user){
        userRepository.save(user);
    }


    public void deleteById(int id){
        userRepository.deleteById(id);
    }
}
