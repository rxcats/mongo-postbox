package io.github.rxcats.postbox.repository;

import io.github.rxcats.postbox.entity.Item;
import io.github.rxcats.postbox.entity.PostMessage;
import io.github.rxcats.postbox.entity.Sender;
import io.github.rxcats.postbox.entity.UserPost;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserPostRepositoryTest {

    @Autowired
    private UserPostRepository userPostRepository;

    @Autowired
    private UserPostCustomRepository userPostCustomRepository;

    @Autowired
    private UserPostNoCustomRepository userPostNoCustomRepository;

    @Test
    public void insertTest() {
        final String testUserId = "50987";

        long postNo = userPostNoCustomRepository.getNextNo(testUserId);

        UserPost expected = new UserPost();
        expected.setUserId(testUserId);
        expected.setUserPostId(UserPost.userPostId(testUserId, postNo));
        expected.setPostNo(postNo);
        expected.setCreatedDate(LocalDateTime.now());
        expected.setExpireAt(LocalDateTime.now().plusMonths(6));
        expected.setSender(sender());
        expected.setItems(items());
        expected.setMessage(message());

        UserPost actual = userPostRepository.insert(expected);
        assertThat(actual).isEqualTo(expected);

        List<UserPost> all = userPostCustomRepository.getAll(testUserId);
        assertThat(all).contains(actual);
    }

    private PostMessage message() {
        PostMessage message = new PostMessage();
        message.setTitle("2018/01/01 서버 정검 보상");
        message.setBody("안녕하세요. [GM 애니] 에요. 2018월 1월 1일 서버 정검으로 인해 아이템을 보내드립니다.");
        return message;
    }

    private List<Item> items() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("I1001", 1));
        return items;
    }

    private Sender sender() {
        Sender sender = new Sender();
        sender.setSenderId("GM1001");
        sender.setAccountId("gm1001@rxcats.com");
        sender.setName("GM 애니");
        return sender;
    }

}
