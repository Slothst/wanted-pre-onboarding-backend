package wantedpreonboardingbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ApplyDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        private Long recruitmentId;
        private Long userId;
    }
}
