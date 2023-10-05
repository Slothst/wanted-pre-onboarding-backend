package wantedpreonboardingbackend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruitmentId;

    @Column(nullable = false)
    private String recruitmentPosition;

    @Column(nullable = false)
    private Long recruitmentReward;

    @Column(nullable = false)
    private String recruitmentContent;

    @Column(nullable = false)
    private String skill;
}
