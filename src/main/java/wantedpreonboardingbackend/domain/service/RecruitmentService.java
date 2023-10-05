package wantedpreonboardingbackend.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wantedpreonboardingbackend.domain.dto.RecruitmentDto;
import wantedpreonboardingbackend.domain.entity.Recruitment;
import wantedpreonboardingbackend.domain.mapper.RecruitmentMapper;
import wantedpreonboardingbackend.domain.repository.RecruitmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentMapper recruitmentMapper;
    private final CompanyService companyService;

    public void createRecruitment(RecruitmentDto.Post post) {
        Recruitment recruitment = recruitmentMapper.postDtoToRecruitment(post);
        recruitment.setCompany(companyService.findVerifiedCompany(post.getCompanyId()));
        recruitmentRepository.save(recruitment);
    }

    @Transactional
    public void updateRecruitment(RecruitmentDto.Patch patch, Long recruitmentId) {
        Recruitment recruitment = recruitmentMapper.patchDtoToRecruitment(patch);
        Recruitment verifiedRecruitment = findVerifiedRecruitment(recruitmentId);

        Optional.ofNullable(recruitment.getRecruitmentPosition())
                .ifPresent(verifiedRecruitment::setRecruitmentPosition);
        Optional.ofNullable(recruitment.getRecruitmentReward())
                .ifPresent(verifiedRecruitment::setRecruitmentReward);
        Optional.ofNullable(recruitment.getRecruitmentContent())
                .ifPresent(verifiedRecruitment::setRecruitmentContent);
        Optional.ofNullable(recruitment.getSkill())
                .ifPresent(verifiedRecruitment::setSkill);
        verifiedRecruitment.setRecruitmentId(recruitmentId);
    }

    public RecruitmentDto.DetailPage getDetailPage(Long recruitmentId) {
        Recruitment findrecruitment = findVerifiedRecruitment(recruitmentId);
        RecruitmentDto.DetailPage detailPage = recruitmentMapper.recruitmentToDetailPage(findrecruitment);
        List<Recruitment> recruitments = findrecruitment.getCompany().getRecruitments();

        // 회사의 다른 채용공고 가져오기
        if (recruitments.size() == 1) {
            return detailPage;
        }
        List<Long> others = new ArrayList<>();
        for (Recruitment recruitment : recruitments) {
            if (recruitment.getRecruitmentId() == recruitmentId) {
                continue;
            }
            others.add(recruitment.getRecruitmentId());
        }
        detailPage.setOtherRecruitments(others);

        return detailPage;
    }

    public List<RecruitmentDto.Response> getRecruitmentList() {
        return recruitmentMapper.recruitmentToResponses(recruitmentRepository.findAll());
    }

    public List<RecruitmentDto.Response> searchRecruitments(String keyword) {
        return recruitmentMapper.recruitmentToResponses(recruitmentRepository.findAllSearch(keyword));
    }

    public void deleteRecruitment(Long recruitmentId) {
        recruitmentRepository.delete(findVerifiedRecruitment(recruitmentId));
    }

    public Recruitment findVerifiedRecruitment(Long recruitmentId) {
        return recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("Recruitment not exists"));
    }
}
