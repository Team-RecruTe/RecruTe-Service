package com.blanc.recrute.recruitment.service;

import com.blanc.recrute.recruitment.dao.RecruitDAO;
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
}
