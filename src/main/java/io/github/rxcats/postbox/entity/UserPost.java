package io.github.rxcats.postbox.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@CompoundIndexes({
    @CompoundIndex(name = "userIdAndPostNoIndex", def = "{userId : 1, postNo : -1}")
})
@Document
public class UserPost {

    @Id
    private String userPostId;

    private long postNo;

    private String userId;

    private Sender sender;

    private PostMessage messages;

    private List<Item> items;

    private LocalDateTime expireAt;

    private LocalDateTime readDate;

    private LocalDateTime deletedDate;

    private LocalDateTime createdDate;

    public static String userPostId(String userId, long postNo) {
        return userId + "-" + postNo;
    }

}
