package com.blanc.recrute.exam.service;

import com.blanc.recrute.exam.dao.ExamDAO;
import com.blanc.recrute.exam.dto.ExamInfoDTO;

public class ExamService {

  private static ExamDAO examDAO = new ExamDAO();

  public ExamInfoDTO getExamContent(Integer recruitId) {

    return recruitId != null ? examDAO.getExamContent(recruitId) : null;

  }

}
