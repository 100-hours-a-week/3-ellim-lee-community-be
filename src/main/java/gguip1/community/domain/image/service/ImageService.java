package gguip1.community.domain.image.service;

import gguip1.community.domain.image.dto.PresignedImageUploadResponse;
import gguip1.community.domain.image.entity.Image;
import gguip1.community.domain.image.repository.ImageRepository;
import gguip1.community.global.exception.ErrorCode;
import gguip1.community.global.exception.ErrorException;
import gguip1.community.global.infra.S3Manager;
import gguip1.community.global.infra.dto.PresignedUrlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final S3Manager s3Manager;

    public PresignedImageUploadResponse getPresignedUrl(String type, String originalFilename) {
        if (type.equals("profile")) {
            String s3AccessKey = createUniqueFilename(type, originalFilename);
            Image image = imageRepository.save(
                    Image.builder()
                            .s3_key(s3AccessKey)
                            .build()
            );

            PresignedUrlResponse s3Response = s3Manager.getPresignedUrl(s3AccessKey);

            return new PresignedImageUploadResponse(
                s3Response.presignedUrl(),
                s3Response.key(),
                image.getImageId()
            );
        } else if (type.equals("post")) {
            String s3AccessKey = createUniqueFilename(type, originalFilename);
            Image image = imageRepository.save(
                    Image.builder()
                            .s3_key(s3AccessKey)
                            .build()
            );

            PresignedUrlResponse s3Response = s3Manager.getPresignedUrl(s3AccessKey);

            return new PresignedImageUploadResponse(
                s3Response.presignedUrl(),
                s3Response.key(),
                image.getImageId()
            );
        } else {
            throw new ErrorException(
                    ErrorCode.BAD_REQUEST
            );
        }
    }

    private String createUniqueFilename(String prefix, String originalFileName) {
        return String.format("%s/original/%s_%s", prefix, UUID.randomUUID(), originalFileName);
    }
}
