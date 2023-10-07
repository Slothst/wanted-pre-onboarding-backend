package wantedpreonboardingbackend.domain.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import wantedpreonboardingbackend.domain.entity.Apply;
import wantedpreonboardingbackend.domain.entity.Company;
import wantedpreonboardingbackend.domain.entity.Recruitment;
import wantedpreonboardingbackend.domain.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ApplyRepositoryTest {
    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecruitmentRepository recruitmentRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @DisplayName("지원서류 생성 테스트")
    @Test
    void saveApply() {
        // given
        User user = user();
        Recruitment recruitment = recruitment();
        Apply apply = new Apply();
        apply.setUser(user);
        apply.setRecruitment(recruitment);

        // when
        Apply savedApply = applyRepository.save(apply);

        // then
        assertThat(apply).isSameAs(savedApply);
        assertThat(apply.getApplyId()).isNotNull();
        assertThat(apply.getUser()).isSameAs(savedApply.getUser());
        assertThat(apply.getRecruitment()).isSameAs(savedApply.getRecruitment());
    }

    @DisplayName("지원한 적이 있는지 검사")
    @Test
    void findByUserAndRecruitment() {
        // given
        User user = user();
        userRepository.save(user);
        Recruitment recruitment = recruitment();
        recruitmentRepository.save(recruitment);
        Apply apply = new Apply();
        apply.setUser(user);
        apply.setRecruitment(recruitment);
        applyRepository.save(apply);

        // when
        Apply findApply = applyRepository.findByUserAndRecruitment(user, recruitment);

        // then
        assertThat(apply).isSameAs(findApply);
    }

    private User user() {
        // given
        User user = new User();
        user.setName("김철수");
        user.setGender("남");
        user.setAge(25);
        return user;
    }

    private Recruitment recruitment() {
        Company company = company();
        Recruitment recruitment = new Recruitment();
        recruitment.setRecruitmentPosition("백엔드 주니어 개발자");
        recruitment.setRecruitmentReward(500000L);
        recruitment.setRecruitmentContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..");
        recruitment.setSkill("Python");
        recruitment.setCompany(company);
        return recruitment;
    }

    private Company company() {
        Company company = new Company();
        company.setCompanyName("원티드랩");
        company.setNation("한국");
        company.setRegion("서울");
        return companyRepository.save(company);
    }
}