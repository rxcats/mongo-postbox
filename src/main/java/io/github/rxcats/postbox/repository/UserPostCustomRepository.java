package io.github.rxcats.postbox.repository;

import io.github.rxcats.postbox.entity.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserPostCustomRepository {

    @Autowired
    private MongoOperations mongoOperations;

    public List<UserPost> getAll(String userId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));

        return mongoOperations.find(query, UserPost.class);

    }

}
