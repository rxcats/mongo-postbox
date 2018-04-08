package io.github.rxcats.postbox.repository;

import io.github.rxcats.postbox.entity.UserPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPostRepository extends MongoRepository<UserPost, String> {

}
