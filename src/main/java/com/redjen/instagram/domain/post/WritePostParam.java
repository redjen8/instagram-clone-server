package com.redjen.instagram.domain.post;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class WritePostParam {

    private Long authorId;
    private String postBody;
    private List<String> postPhotoUrlList;
}
