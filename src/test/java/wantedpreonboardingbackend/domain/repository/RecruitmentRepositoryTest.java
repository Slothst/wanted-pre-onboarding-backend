package wantedpreonboardingbackend.domain.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import wantedpreonboardingbackend.domain.entity.Company;
import wantedpreonboardingbackend.domain.entity.Recruitment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecruitmentRepositoryTest {
    @Autowired
    private RecruitmentRepository recruitmentRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @DisplayName("채용공고 생성 테스트")
    @Test
    void saveRecruitment() {
        //given
        Company company = company();
        Recruitment recruitment = new Recruitment();
        recruitment.setRecruitmentPosition("백엔드 주니어 개발자");
        recruitment.setRecruitmentReward(500000L);
        recruitment.setRecruitmentContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..");
        recruitment.setSkill("Python");
        recruitment.setCompany(company);

        // when
        Recruitment savedRecruitment = recruitmentRepository.save(recruitment);

        // then
        assertThat(recruitment).isSameAs(savedRecruitment);
        assertThat(recruitment.getRecruitmentId()).isNotNull();
        assertThat(recruitment.getRecruitmentPosition()).isEqualTo(savedRecruitment.getRecruitmentPosition());
        assertThat(recruitment.getRecruitmentReward()).isEqualTo(savedRecruitment.getRecruitmentReward());
        assertThat(recruitment.getRecruitmentContent()).isEqualTo(savedRecruitment.getRecruitmentContent());
        assertThat(recruitment.getCompany()).isEqualTo(savedRecruitment.getCompany());
    }

    @DisplayName("채용공고 검색 테스트")
    @Test
    void findAllSearch() {
        // given
        Company company = company();
        companyRepository.save(company);
        Recruitment recruitment1 = new Recruitment();
        recruitment1.setRecruitmentPosition("백엔드 주니어 개발자");
        recruitment1.setRecruitmentReward(500000L);
        recruitment1.setRecruitmentContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..");
        recruitment1.setSkill("Python");
        recruitment1.setCompany(company);
        recruitmentRepository.save(recruitment1);

        Recruitment recruitment2 = new Recruitment();
        recruitment2.setRecruitmentPosition("백엔드 시니어 개발자");
        recruitment2.setRecruitmentReward(500000L);
        recruitment2.setRecruitmentContent("원티드에서 백엔드 시니어 개발자를 채용합니다. 자격요건은..");
        recruitment2.setSkill("Python");
        recruitment2.setCompany(company);
        recruitmentRepository.save(recruitment2);

        // when
        List<Recruitment> recruitmentList = recruitmentRepository.findAllSearch("시니어");

        // then
        assertThat(recruitmentList.size()).isEqualTo(1);
    }

    private Company company() {
        Company company = new Company();
        company.setCompanyName("원티드랩");
        company.setNation("한국");
        company.setRegion("서울");
        return company;
    }
}