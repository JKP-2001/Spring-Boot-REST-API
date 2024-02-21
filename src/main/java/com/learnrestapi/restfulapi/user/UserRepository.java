package com.learnrestapi.restfulapi.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface UserRepository extends JpaRepository<User, Integer> {

}
