package wantedpreonboardingbackend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(length = 20, nullable = false)
    private String companyName;

    @Column(length = 20, nullable = false)
    private String nation;

    @Column(length = 20, nullable = false)
    private String region;

    @OneToMany(mappedBy = "company")
    private List<Recruitment> recruitments;
}
