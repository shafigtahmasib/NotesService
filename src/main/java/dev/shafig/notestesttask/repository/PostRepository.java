package dev.shafig.notestesttask.repository;

import dev.shafig.notestesttask.model.document.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
