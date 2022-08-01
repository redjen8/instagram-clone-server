package com.redjen.instagram.post;

import com.redjen.instagram.domain.entity.Member;
import com.redjen.instagram.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Date;

@SpringBootTest
public class PostTest {

    private PostService postService;
    private PasswordEncoder passwordEncoder;

    private Member testMember;

    @PostConstruct
    public void init() {
        testMember = Member.builder()
                .userId("test")
                .name("test")
                .password(passwordEncoder.encode("1234"))
                .isSSO(false)
                .isPublic(true)
                .isActive(true)
                .birthDate(new Date())
                .phoneNumber("010-1111-1111")
                .build();
    }

    @Test
    public void writeNewPostTest() {

    }

    @Test
    public void deleteNewPostTest() {

    }

    @Test
    public void modifyPostTest() {

    }

    @Test
    public void getPostListTest() {

    }
}
