package wantedpreonboardingbackend.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import wantedpreonboardingbackend.domain.dto.RecruitmentDto;
import wantedpreonboardingbackend.domain.entity.Recruitment;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecruitmentMapper {
    Recruitment postDtoToRecruitment(RecruitmentDto.Post post);

    Recruitment patchDtoToRecruitment(RecruitmentDto.Patch patch);

    RecruitmentDto.Response recruitmentToResponse(Recruitment recruitment);

    List<RecruitmentDto.ResponseList> recruitmentToResponseList(Recruitment recruitment);
}
