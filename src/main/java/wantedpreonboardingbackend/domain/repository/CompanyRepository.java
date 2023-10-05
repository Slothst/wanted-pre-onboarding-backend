package wantedpreonboardingbackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wantedpreonboardingbackend.domain.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
