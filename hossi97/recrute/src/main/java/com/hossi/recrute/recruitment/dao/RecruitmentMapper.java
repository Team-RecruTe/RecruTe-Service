package com.hossi.recrute.recruitment.dao;

import com.hossi.recrute.recruitment.dto.ApplicantReqDto;
import com.hossi.recrute.recruitment.dto.RecruitmentDto;

public interface RecruitmentMapper {
    RecruitmentDto selectRecruitmentByRctId(Integer rctId);
    int insertApplicant(ApplicantReqDto applicantReqDto);

}
