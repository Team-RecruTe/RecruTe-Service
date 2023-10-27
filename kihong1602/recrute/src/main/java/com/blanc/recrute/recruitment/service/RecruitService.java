package com.blanc.recrute.recruitment.service;

import com.blanc.recrute.common.AptIdFactory;
import com.blanc.recrute.recruitment.dao.RecruitDAO;
import com.blanc.recrute.recruitment.dto.ApplyDTO;
import com.blanc.recrute.recruitment.dto.ApplyInfoDTO;
import com.blanc.recrute.recruitment.dto.DetailDTO;
import com.blanc.recrute.recruitment.dto.RecruitDTO;

public class RecruitService {

    private static final RecruitDAO recruitDAO = new RecruitDAO();

    public DetailDTO selectDetail(Integer id) {

        if (id != null) {
            RecruitDTO recruitDTO = new RecruitDTO.Builder().id(id).build();

            return recruitDAO.selectDetail(recruitDTO);
        }

        return null;
    }

    public String applyRecruit(ApplyInfoDTO applyInfoDTO, String memberId) {
        StringBuilder sb = new StringBuilder();

        Integer memberRealId = recruitDAO.searchMemberId(memberId);

        String aptId = AptIdFactory.createAptId(applyInfoDTO.getRecruitId(), applyInfoDTO.getCompanyId(), memberRealId);

        ApplyDTO applyDto = new ApplyDTO.Builder().aptId(aptId).recruitId(applyInfoDTO.getRecruitId()).memberId(memberRealId).build();
        int result = recruitDAO.applyRecruit(applyDto);


        return result > 0 ? sb.append("success").toString() : sb.append("fail").toString();
    }
}
