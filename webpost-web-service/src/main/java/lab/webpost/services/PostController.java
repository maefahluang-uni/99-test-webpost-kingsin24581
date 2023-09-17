package lab.webpost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lab.webpost.domain.Post;

@RestController
@RequestMapping("/posts") // Set the base path for all endpoints in this controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    // Get all Posts
    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postRepository.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Get post by ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Optional<Post> postOpt = postRepository.findById(id);
        if (postOpt.isPresent()) {
            return new ResponseEntity<>(postOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Find by title
    @GetMapping("/title/{title}") // Added "/title" to distinguish from the previous endpoint
    public ResponseEntity<List<Post>> getPostByTitle(@PathVariable String title) {
        List<Post> posts = postRepository.findByTitle(title);
        if (!posts.isEmpty()) {
            return ResponseEntity.ok(posts);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add new post
    @PostMapping
    public ResponseEntity<String> addPost(@RequestBody Post post) {
        postRepository.save(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Delete post by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete all posts
    @DeleteMapping
    public ResponseEntity<String> deleteAllPosts() {
        postRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
