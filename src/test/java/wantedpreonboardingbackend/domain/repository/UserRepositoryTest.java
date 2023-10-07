package wantedpreonboardingbackend.domain.repository;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import wantedpreonboardingbackend.domain.entity.Company;
import wantedpreonboardingbackend.domain.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @DisplayName("User 생성 테스트")
    @Test
    void saveUser() {
        // given
        User user = new User();
        user.setName("김철수");
        user.setGender("남");
        user.setAge(25);

        // when
        User savedUser = userRepository.save(user);

        // then
        assertThat(user).isSameAs(savedUser);
        assertThat(user.getUserId()).isNotNull();
        assertThat(user.getName()).isEqualTo(savedUser.getName());
        assertThat(user.getGender()).isEqualTo(savedUser.getGender());
        assertThat(user.getAge()).isEqualTo(savedUser.getAge());
    }
}