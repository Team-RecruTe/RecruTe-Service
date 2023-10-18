package com.hossi.recrute.recruitment.service;

import com.hossi.recrute.recruitment.dao.RecruitmentDao;
import com.hossi.recrute.recruitment.dto.RecruitmentDto;

public class RecruitmentService {
    private final RecruitmentDao recruitmentDao = new RecruitmentDao();

    public RecruitmentDto getRecruitment(Integer rctId) {
        return recruitmentDao.getRecruitmentByRctId(rctId);
    }

}
