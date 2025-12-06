package gguip1.community.domain.post.mapper;

import gguip1.community.domain.post.dto.request.PostCreateRequest;
import gguip1.community.domain.post.dto.response.AuthorResponse;
import gguip1.community.domain.post.dto.response.PostPageItemResponse;
import gguip1.community.domain.post.entity.Post;
import gguip1.community.domain.user.entity.User;
import gguip1.community.global.util.ImageUriProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper {
    private final ImageUriProvider imageUriProvider;

    public Post fromPostRequest(PostCreateRequest postCreateRequest, User user){
        return Post.builder()
                .user(user)
                .title(postCreateRequest.title())
                .content(postCreateRequest.content())
                .build();
    }

    public PostPageItemResponse toPostPageItemResponse(Post post, User user, Integer likeCount, Integer commentCount, Integer viewCount){
        String imageKey = user.getProfileImage() != null ? user.getProfileImage().getS3_key() : null;
        String fullUrl = imageUriProvider.generateUrl(imageKey);

        return PostPageItemResponse.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(
                        new AuthorResponse(
                                user.getNickname(),
                                user.getProfileImage() != null ? fullUrl : null
                        )
                )
                .createdAt(post.getCreatedAt())
                .likeCount(likeCount)
                .commentCount(commentCount)
                .viewCount(viewCount)
                .build();
    }
}
