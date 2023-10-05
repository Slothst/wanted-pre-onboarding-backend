package wantedpreonboardingbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class RecruitmentDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        private Long companyId;
        private String recruitmentPosition;
        private Long recruitmentReward;
        private String recruitmentContent;
        private String skill;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private String recruitmentPosition;
        private Long recruitmentReward;
        private String recruitmentContent;
        private String skill;
    }

    @Getter
    @Setter
    public static class Response {
        private Long recruitmentId;
        private String companyName;
        private String nation;
        private String region;
        private String recruitmentPosition;
        private String recruitmentReward;
        private String skill;
    }

    @Getter
    @Setter
    public static class ResponseList {
        private Long recruitmentId;
        private String companyName;
        private String nation;
        private String region;
        private String recruitmentPosition;
        private String recruitmentReward;
        private String skill;
        private String recruitmentContent;
        private List<Long> otherRecruitments;
    }
}
