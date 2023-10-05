package wantedpreonboardingbackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wantedpreonboardingbackend.domain.entity.Recruitment;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    @Query(
            value = "SELECT p FROM Recruitment p WHERE p.company.companyName LIKE %:keyword% OR p.recruitmentPosition LIKE %:keyword% OR p.skill LIKE %:keyword%",
            countQuery = "SELECT COUNT(p.id) FROM Recruitment p WHERE p.company.companyName LIKE %:keyword% OR p.recruitmentPosition LIKE %:keyword% OR p.skill LIKE %:keyword%"
    )
    List<Recruitment> findAllSearch(String keyword);
}
