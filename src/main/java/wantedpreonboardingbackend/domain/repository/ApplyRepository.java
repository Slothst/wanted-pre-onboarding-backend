package wantedpreonboardingbackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wantedpreonboardingbackend.domain.entity.Apply;
import wantedpreonboardingbackend.domain.entity.Recruitment;
import wantedpreonboardingbackend.domain.entity.User;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    Apply findByUserAndRecruitment(User user, Recruitment recruitment);
}
