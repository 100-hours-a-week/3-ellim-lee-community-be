package gguip1.community.domain.post.mapper;

import gguip1.community.domain.image.dto.ImageResponse;
import gguip1.community.domain.image.entity.Image;
import gguip1.community.domain.post.entity.Post;
import gguip1.community.domain.post.entity.PostImage;
import gguip1.community.domain.post.id.PostImageId;
import gguip1.community.global.util.ImageUriProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostImageMapper {
    private final ImageUriProvider imageUriProvider;

    public PostImage toEntity(Post post, Image image, byte imageOrder){
        return PostImage.builder()
                .postImageId(new PostImageId(post.getPostId(), image.getImageId()))
                .post(post)
                .image(image)
                .imageOrder(imageOrder)
                .build();
    }

    public ImageResponse toImageResponse(PostImage postImage){
        Image image = postImage.getImage();

        String imageKey = image.getS3_key();
        String fullUrl = imageUriProvider.generateUrl(imageKey);

        return ImageResponse.builder()
                .imageId(image.getImageId())
                .imageUrl(fullUrl)
                .build();
    }
}
