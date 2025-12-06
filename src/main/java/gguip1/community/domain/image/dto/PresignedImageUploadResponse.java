package gguip1.community.domain.image.dto;

public record PresignedImageUploadResponse(
        String presignedUrl,
        String key,
        Long imageId
) {
}
