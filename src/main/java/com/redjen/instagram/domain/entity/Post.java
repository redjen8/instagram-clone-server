package com.redjen.instagram.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
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

    @OneToMany(mappedBy = "post")
    private List<PostPhoto> photoList = new ArrayList<>();
}
