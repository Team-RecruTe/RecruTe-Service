package com.blanc.recrute.recruitment.service;

import com.blanc.recrute.common.AptIdFactory;
import com.blanc.recrute.recruitment.dao.ApplyDAO;
import com.blanc.recrute.recruitment.dto.ApplyDTO;
import com.blanc.recrute.recruitment.dto.ApplyInfoDTO;

public class ApplyService {

    private static final ApplyDAO applyDAO = new ApplyDAO();

    public String applyRecruit(ApplyInfoDTO applyInfoDTO, String memberId) {
        StringBuilder sb = new StringBuilder();

        Integer memberRealId = applyDAO.searchMemberId(memberId);

        String aptId = AptIdFactory.createAptId(applyInfoDTO.getRecruitId(), applyInfoDTO.getCompanyId(), memberRealId);

        ApplyDTO applyDto = new ApplyDTO.Builder().aptId(aptId).recruitId(applyInfoDTO.getRecruitId()).memberId(memberRealId).build();
        int result = applyDAO.applyRecruit(applyDto);


        return result > 0 ? sb.append("success").toString() : sb.append("fail").toString();
    }
}
