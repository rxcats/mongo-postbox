package io.github.rxcats.postbox.repository;

import io.github.rxcats.postbox.entity.UserPostNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserPostNoCustomRepository {

    @Autowired
    private MongoOperations mongoOperations;

    public long getNextNo(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));

        Update update = new Update();
        update.inc("current", 1);

        UserPostNo userPostNo = mongoOperations.findAndModify(
            query,
            update,
            FindAndModifyOptions.options()
                .upsert(true)
                .returnNew(true),
            UserPostNo.class
        );

        return userPostNo.getCurrent();
    }

}
