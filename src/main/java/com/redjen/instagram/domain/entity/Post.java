package com.redjen.instagram.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member author;

    private String body;

    @Column(name="created_at")
    private LocalDateTime createdAt;
}
