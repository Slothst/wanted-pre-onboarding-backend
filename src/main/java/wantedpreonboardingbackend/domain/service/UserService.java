package wantedpreonboardingbackend.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wantedpreonboardingbackend.domain.entity.User;
import wantedpreonboardingbackend.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findVerifiedUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not exists"));
    }
}
