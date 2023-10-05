package wantedpreonboardingbackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wantedpreonboardingbackend.domain.entity.Recruitment;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
}
