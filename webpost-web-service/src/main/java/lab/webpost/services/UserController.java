package lab.webpost.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab.webpost.domain.Post;
import lab.webpost.domain.User;

@RestController
@RequestMapping("/users") // Set the base path for all endpoints in this controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    //TODO: end point for validate user by username 
    @GetMapping("/{username}")
    public ResponseEntity<List<User>> getUserByUsername(@PathVariable String username) {
        // Retrieve the user by username from the UserRepository
        List<User> user = userRepository.findByUsername(username);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Add new post
    @PostMapping
    public ResponseEntity<String> addPost(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

    
    
