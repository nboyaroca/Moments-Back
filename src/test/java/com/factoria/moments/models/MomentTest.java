package com.factoria.moments.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MomentTest {

    @Test
    void shouldHaveACommentsCounter() {
        var moment = new Moment();
        var comment = new Comment();
        moment.addComment(comment);
        var sut = moment.commentsCount();

        assertThat(sut, equalTo(1));
    }

    @Test
    void shouldHaveALikesCounter() {
        var moment = new Moment();
        var user = new User();
        moment.setId(1L);
        user.setId(1L);
        var like = new Like(user, moment);
        moment.addLike(like);

        int sut = moment.likesCount();

        assertThat(sut, equalTo(1));
        /*assertThat(sut, equalTo(2)); FAIL TEST */
    }
}