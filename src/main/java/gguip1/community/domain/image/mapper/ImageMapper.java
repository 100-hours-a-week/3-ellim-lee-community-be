package gguip1.community.domain.image.mapper;

import gguip1.community.domain.image.entity.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    public Image toEntity(String s3_key) {
        return Image.builder()
                .s3_key(s3_key)
                .build();
    }
}
