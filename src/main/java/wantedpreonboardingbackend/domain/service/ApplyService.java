package wantedpreonboardingbackend.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wantedpreonboardingbackend.domain.dto.ApplyDto;
import wantedpreonboardingbackend.domain.entity.Apply;
import wantedpreonboardingbackend.domain.repository.ApplyRepository;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final UserService userService;
    private final RecruitmentService recruitmentService;

    public void createApply(ApplyDto.Post post) {
        Apply apply = new Apply();
        apply.setUser(userService.findVerifiedUser(post.getUserId()));
        apply.setRecruitment(recruitmentService.findVerifiedRecruitment(post.getRecruitmentId()));

        // 이미 있다면 에러 던지기
        if (applyRepository.findByUserAndRecruitment(apply.getUser(), apply.getRecruitment()) != null) {
            throw new RuntimeException("You've already applied this company!");
        }

        else applyRepository.save(apply);
    }
}
