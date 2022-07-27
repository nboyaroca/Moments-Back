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

    // BAD PATH
    @Test
    void momentShouldNotLetAddLikeIfMomentDoesNotMatch() {
        var moment1 = new Moment();
        var moment2 = new Moment();
        var user = new User();
        /* moment1.setId(1L);
        moment2.setId(2L);*/
        user.setId(1L);
        var like = new Like(user, moment1);
        moment2.addLike(like);

        int sut = moment2.likesCount();

        assertThat(sut, equalTo(0));
        /*assertThat(sut, equalTo(1)); FAIL TEST */
    }

    // HAPPY PATH
    @Test
    void momentShouldKnowIfUserLikesIts() {
        var moment = new Moment();
        var liker = new User();
        var like = new Like(liker, moment);
        moment.addLike(like);
        boolean sut = moment.isLiked(liker);

        assertThat(sut, equalTo(true));
        /*assertThat(sut, equalTo(false)); FAIL TEST */
    }

    // BAD PATH
    @Test
    void LikerShouldBeIncludedInLikeListToAppear() {
        var moment = new Moment();
        var liker = new User();
        var notLiker = new User();
        var like = new Like(liker, moment);
        moment.addLike(like);
        boolean sut = moment.isLiked(notLiker);

        assertThat(sut, equalTo(false));
        /*assertThat(sut, equalTo(true)); FAIL TEST */
    }
}