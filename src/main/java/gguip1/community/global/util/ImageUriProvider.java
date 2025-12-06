package gguip1.community.global.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageUriProvider {

    private final String domainUrl;

    public ImageUriProvider(@Value("${cloud.aws.s3.domain}") String domainUrl) {
        this.domainUrl = domainUrl;
    }

    public String generateUrl(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }
        return "%s/%s".formatted(domainUrl, key);
    }
}
