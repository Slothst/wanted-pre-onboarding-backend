package wantedpreonboardingbackend.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import wantedpreonboardingbackend.domain.dto.RecruitmentDto;
import wantedpreonboardingbackend.domain.entity.Recruitment;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecruitmentMapper {
    default Recruitment postDtoToRecruitment(RecruitmentDto.Post post) {
        if (post == null) {
            return null;
        } else {
            Recruitment recruitment = new Recruitment();
            recruitment.setRecruitmentPosition(post.getRecruitmentPosition());
            recruitment.setRecruitmentReward(post.getRecruitmentReward());
            recruitment.setRecruitmentContent(post.getRecruitmentContent());
            recruitment.setSkill(post.getSkill());

            return recruitment;
        }
    }
    Recruitment patchDtoToRecruitment(RecruitmentDto.Patch patch);

    default RecruitmentDto.DetailPage recruitmentToDetailPage(Recruitment recruitment) {
        if (recruitment == null) {
            return null;
        } else {
            RecruitmentDto.DetailPage detailPage = new RecruitmentDto.DetailPage();
            detailPage.setRecruitmentId(recruitment.getRecruitmentId());
            detailPage.setCompanyName(recruitment.getCompany().getCompanyName());
            detailPage.setNation(recruitment.getCompany().getNation());
            detailPage.setRegion(recruitment.getCompany().getRegion());
            detailPage.setRecruitmentPosition(recruitment.getRecruitmentPosition());
            detailPage.setRecruitmentReward(recruitment.getRecruitmentReward());
            detailPage.setSkill(recruitment.getSkill());
            detailPage.setRecruitmentContent(recruitment.getRecruitmentContent());

            return detailPage;
        }
    }

    default RecruitmentDto.Response recruitmentToResponse(Recruitment recruitment) {
        if (recruitment == null) {
            return null;
        } else {
            RecruitmentDto.Response response = new RecruitmentDto.Response();
            response.setRecruitmentId(recruitment.getRecruitmentId());
            response.setCompanyName(recruitment.getCompany().getCompanyName());
            response.setNation(recruitment.getCompany().getNation());
            response.setRegion(recruitment.getCompany().getRegion());
            response.setRecruitmentPosition(recruitment.getRecruitmentPosition());
            response.setRecruitmentReward(recruitment.getRecruitmentReward());
            response.setSkill(recruitment.getSkill());

            return response;
        }
    }
    List<RecruitmentDto.Response> recruitmentToResponses(List<Recruitment> recruitments);
}
