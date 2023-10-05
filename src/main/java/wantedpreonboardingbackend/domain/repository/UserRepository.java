package wantedpreonboardingbackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wantedpreonboardingbackend.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
