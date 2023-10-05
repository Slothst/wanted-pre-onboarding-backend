package wantedpreonboardingbackend.domain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wantedpreonboardingbackend.domain.dto.ApplyDto;
import wantedpreonboardingbackend.domain.entity.Apply;
import wantedpreonboardingbackend.domain.service.ApplyService;

@RestController
@RequestMapping("/applies")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping
    public void createApply(@RequestBody ApplyDto.Post post) {
        applyService.createApply(post);
    }
}
