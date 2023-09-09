package lab.webpost.services;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lab.webpost.domain.Post;
import lab.webpost.domain.User;

public interface PostRepository extends CrudRepository<Post, Long>{
        //TODO: add necessary devied methods
        public List<Post> findAll();
        public List<Post> findByTitleStartingWith(String prefix);
        public List<Post> findByTitle(String Title);
}
