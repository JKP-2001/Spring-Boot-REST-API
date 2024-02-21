package com.learnrestapi.restfulapi.user;

import com.learnrestapi.restfulapi.response.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private UserDaoService userDaoService;
    private UserRepositoryService userRepositoryService;
    private PostRepository postRepository;

    public UserController(UserDaoService userDaoService,
                          UserRepositoryService userRepositoryService, PostRepository postRepository) 
    {

        this.userDaoService = userDaoService;
        this.userRepositoryService = userRepositoryService;
        this.postRepository = postRepository;
    }

    @GetMapping(path = "/users")
    public ResponseEntity<Object> getAllUsers(){
        List<User> users = userDaoService.findAll();

        return ResponseHandler.generateResponse("Successfully fetch users",
                HttpStatus.OK,
                users);
    }

    @GetMapping(path = "/jpa/users")
    public ResponseEntity<Object> getAllUsersJPA(){
        List<User> users = userRepositoryService.findAll();

        return ResponseHandler.generateResponse("Successfully fetch users",
                HttpStatus.OK,
                users);
    }

    @GetMapping(path = "/jpa/users/{id}")
    public ResponseEntity<Object> retrieveJpaUserById(@PathVariable int id){
        User user = userRepositoryService.getUserById(id);

        if(user == null){
            return ResponseHandler.generateResponse("User with id = "+id+", not found.",
                    HttpStatus.NOT_FOUND,null);
        }

        return ResponseHandler.generateResponse("Successfully fetch user",HttpStatus.OK,
                user);
    }


    @GetMapping(path = "/users/{id}")
    public ResponseEntity<Object> retrieveUserById(@PathVariable int id){
        User user = userDaoService.getUserById(id);

        if(user == null){
            return ResponseHandler.generateResponse("User with id = "+id+", not found.",
                    HttpStatus.NOT_FOUND,null);
        }

        return ResponseHandler.generateResponse("Successfully fetch user",HttpStatus.OK,
                user);
    }

    @PostMapping(path = "/users/new")
    public void addUser(@Valid @RequestBody User user){
        userDaoService.save(user);
    }

    @PostMapping(path = "/jpa/users/new")
    public void addJpaUser(@Valid @RequestBody User user){
        userRepositoryService.save(user);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
        User user = userDaoService.getUserById(id);

        if(user == null){
            return ResponseHandler.generateResponse("User with id = "+id+", not found.",
                    HttpStatus.NOT_FOUND,null);
        }

        userDaoService.deleteById(id);

        return ResponseHandler.generateResponse("User deleted successfully.",
                HttpStatus.OK, null);
    }


    @DeleteMapping(path = "/jpa/users/{id}")
    public ResponseEntity<Object> deleteJpaUser(@PathVariable int id){
        User user = userRepositoryService.getUserById(id);

        if(user == null){
            return ResponseHandler.generateResponse("User with id = "+id+", not found.",
                    HttpStatus.NOT_FOUND,null);
        }

        userRepositoryService.deleteById(id);

        return ResponseHandler.generateResponse("User deleted successfully.",
                HttpStatus.OK, null);
    }

    @GetMapping(path = "/users/{id}/posts")
    public ResponseEntity<Object> retrieveAllPostOfAUser(@PathVariable int id){
        User user = userRepositoryService.getUserById(id);

        if(user == null){
            return ResponseHandler.generateResponse("User with id = "+id+", not found.",
                    HttpStatus.NOT_FOUND,null);
        }

        List<Post> posts = user.getPosts();

        return ResponseHandler.generateResponse("Successfully fetch posts of user with id = "+id,
                HttpStatus.OK, posts);

    }


    @PostMapping(path = "/users/{id}/posts/new")
    public ResponseEntity<Object> createNewPost(@PathVariable int id, @RequestBody Post post){
        // System.out.println("Id = "+id);
        User user = userRepositoryService.getUserById(id);

        if(user == null){
            return ResponseHandler.generateResponse("User with given id not found", HttpStatus.NOT_FOUND, user);
        }

        post.setUser(user);
        post.setDate(LocalDate.now());

        postRepository.save(post);

        return ResponseHandler.generateResponse("Post created successfully", HttpStatus.OK, post);

    }


    @DeleteMapping(path="post/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable int id){
        Post post = postRepository.findById(id).orElse(null);

        if(post == null){
            return ResponseHandler.generateResponse("No post found", HttpStatus.NOT_FOUND, post);
        }

        postRepository.deleteById(id);

        return ResponseHandler.generateResponse("Post successfully deleted", HttpStatus.OK, null);
    }
}
