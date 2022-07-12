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

}