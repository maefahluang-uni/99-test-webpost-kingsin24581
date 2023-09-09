package lab.webpost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lab.webpost.domain.Post;
import lab.webpost.domain.User;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    // TODO: get all Posts
    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postRepository.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //TODO: getting post by id
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById( Long id) {
        // TODO: check if post is null
        Optional<Post> postOpt = postRepository.findById(id);
        if (postOpt.isPresent()) {
            return new ResponseEntity<>(postOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //TODO: find by title
    @GetMapping("/{title}")
    public ResponseEntity<List<Post>> getPostByTitle( String title) {
        List<Post> posts = postRepository.findByTitle(title);
        if (posts != null) {
            return ResponseEntity.ok(posts);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // TODO: adding new post
    @PostMapping
    public ResponseEntity<String> addPost( Post post) {
        postRepository.save(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // TODO: delete post by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost( Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //TODO: delete all posts
    @DeleteMapping
    public ResponseEntity<String> deleteAllPosts() {
        postRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
