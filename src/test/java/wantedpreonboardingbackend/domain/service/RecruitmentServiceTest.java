package wantedpreonboardingbackend.domain.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;
import wantedpreonboardingbackend.domain.dto.RecruitmentDto;
import wantedpreonboardingbackend.domain.entity.Company;
import wantedpreonboardingbackend.domain.entity.Recruitment;
import wantedpreonboardingbackend.domain.mapper.RecruitmentMapper;
import wantedpreonboardingbackend.domain.repository.CompanyRepository;
import wantedpreonboardingbackend.domain.repository.RecruitmentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest
@Transactional
class RecruitmentServiceTest {
    @SpyBean
    private RecruitmentMapper recruitmentMapper;
    @Autowired
    private RecruitmentService recruitmentService;
    @Mock
    private CompanyService companyService;
    @Mock
    private RecruitmentRepository recruitmentRepository;
    @Mock
    private CompanyRepository companyRepository;

    @Test
    @DisplayName("Recruitment 생성 테스트")
    void createRecruitment() {
        System.out.println("## createRecruitment 시작 ##");
        System.out.println();
        // given
        Company company = createCompanyEntity();
        Long fakeCompanyId = 1L;
        ReflectionTestUtils.setField(company, "companyId", fakeCompanyId);

        RecruitmentDto.Post post = createPostDto(company.getCompanyId());
        Recruitment recruitment = createAnyRecruitment(post);
        recruitment.setCompany(company);
        Long fakeRecruitmentId = 1L;
        ReflectionTestUtils.setField(recruitment, "recruitmentId", fakeRecruitmentId);

        // when
        Recruitment recruitment1 = recruitmentService.createRecruitment(post);
        recruitment1.setRecruitmentId(fakeRecruitmentId);
        recruitment1.setCompany(company);

        // then
        assertEquals(recruitment.getRecruitmentId(), recruitment1.getRecruitmentId());
        assertEquals(recruitment.getRecruitmentPosition(), recruitment1.getRecruitmentPosition());
        assertEquals(recruitment.getRecruitmentReward(), recruitment1.getRecruitmentReward());
        assertEquals(recruitment.getRecruitmentContent(), recruitment1.getRecruitmentContent());
        assertEquals(recruitment.getSkill(), recruitment1.getSkill());
        assertEquals(recruitment.getCompany(), recruitment1.getCompany());
    }

    private Company createCompanyEntity() {
        Company company = new Company();
        company.setCompanyName("원티드랩");
        company.setNation("한국");
        company.setRegion("서울");
        return company;
    }

    private RecruitmentDto.Post createPostDto(Long companyId) {
        return new RecruitmentDto.Post(companyId, "백엔드 주니어 개발자", 1500000L, "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..", "Python");
    }

    private Recruitment createAnyRecruitment(RecruitmentDto.Post post) {
        Recruitment recruitment = recruitmentMapper.postDtoToRecruitment(post);
        recruitment.setCompany(companyService.findVerifiedCompany(post.getCompanyId()));
        return recruitment;
    }
}