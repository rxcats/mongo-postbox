package io.github.rxcats.postbox.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UserPostNo {

    @Id
    private String userId;

    private long current;

}
