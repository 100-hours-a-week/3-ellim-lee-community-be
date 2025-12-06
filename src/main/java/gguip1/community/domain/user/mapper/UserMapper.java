package gguip1.community.domain.user.mapper;

import gguip1.community.domain.auth.dto.AuthResponse;
import gguip1.community.domain.image.entity.Image;
import gguip1.community.domain.user.dto.request.UserCreateRequest;
import gguip1.community.domain.user.dto.response.UserResponse;
import gguip1.community.domain.user.dto.response.UserUpdateResponse;
import gguip1.community.domain.user.entity.User;
import gguip1.community.global.util.ImageUriProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ImageUriProvider imageUriProvider;

    public UserResponse toResponse(User user) {
        String imageKey = user.getProfileImage() != null ? user.getProfileImage().getS3_key() : null;
        String fullUrl = imageUriProvider.generateUrl(imageKey);

        return UserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .profileImageUrl(fullUrl)
                .nickname(user.getNickname())
                .build();
    }

    public User fromUserCreateRequest(UserCreateRequest request, String encryptedPassword, Image profileImage) {
        return User.builder()
                .profileImage(profileImage)
                .email(request.email())
                .password(encryptedPassword)
                .nickname(request.nickname())
                .build();
    }

    public AuthResponse toAuthResponse(User user) {
        String imageKey = user.getProfileImage() != null ? user.getProfileImage().getS3_key() : null;
        String fullUrl = imageUriProvider.generateUrl(imageKey);

        return AuthResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .profileImageUrl(fullUrl)
                .nickname(user.getNickname())
                .build();
    }

    public UserUpdateResponse toUserUpdateResponse(User user) {
        String imageKey = user.getProfileImage() != null ? user.getProfileImage().getS3_key() : null;
        String fullUrl = imageUriProvider.generateUrl(imageKey);

        return UserUpdateResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .profileImageUrl(fullUrl)
                .nickname(user.getNickname())
                .build();
    }
}
