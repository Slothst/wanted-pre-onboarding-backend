package wantedpreonboardingbackend.domain.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import wantedpreonboardingbackend.domain.entity.Company;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CompanyRepositoryTest {
    @Autowired
    private CompanyRepository companyRepository;

    @DisplayName("Company 생성 테스트")
    @Test
    void saveCompany() {
        // given
        Company company = new Company();
        company.setCompanyName("원티드랩");
        company.setNation("한국");
        company.setRegion("서울");

        // when
        Company savedCompany = companyRepository.save(company);

        // then
        assertThat(company).isSameAs(savedCompany);
        assertThat(company.getCompanyId()).isNotNull();
        assertThat(company.getCompanyName()).isEqualTo(savedCompany.getCompanyName());
        assertThat(company.getNation()).isEqualTo(savedCompany.getNation());
        assertThat(company.getRegion()).isEqualTo(savedCompany.getRegion());
    }
}