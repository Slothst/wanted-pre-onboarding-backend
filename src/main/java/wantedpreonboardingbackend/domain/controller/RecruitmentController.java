package wantedpreonboardingbackend.domain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wantedpreonboardingbackend.domain.dto.RecruitmentDto;
import wantedpreonboardingbackend.domain.service.RecruitmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/recruitments")
@RequiredArgsConstructor
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    @PostMapping
    public void createRecruitment(@RequestBody @Valid RecruitmentDto.Post post) {
        recruitmentService.createRecruitment(post);
    }

    @PatchMapping("/{recruitment-id}")
    public void updateRecruitment(@PathVariable("recruitment-id") Long recruitmentId, @RequestBody @Valid RecruitmentDto.Patch patch) {
        recruitmentService.updateRecruitment(patch, recruitmentId);
    }

    @GetMapping("/{recruitment-id}")
    public RecruitmentDto.DetailPage getDetailPage(@PathVariable("recruitment-id") Long recruitmentId) {
        return recruitmentService.getDetailPage(recruitmentId);
    }

    @GetMapping
    public List<RecruitmentDto.Response> getRecruitments() {
        return recruitmentService.getRecruitmentList();
    }

    @GetMapping("/search")
    public List<RecruitmentDto.Response> searchRecruitment(@RequestParam String keyword) {
        return recruitmentService.searchRecruitments(keyword);
    }

    @DeleteMapping("/{recruitment-id}")
    public void deleteRecruitment(@PathVariable("recruitment-id") Long recruitmentId) {
        recruitmentService.deleteRecruitment(recruitmentId);
    }
}
