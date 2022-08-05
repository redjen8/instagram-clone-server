package com.redjen.instagram.domain.post;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ModifyPostParam {
    private final Long postId;
    private final Long userId;
    private final String newPostBody;
    private final List<String> newPostPhotoUrl;
}
