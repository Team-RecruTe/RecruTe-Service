package com.blanc.recrute.exam.service;

import com.blanc.recrute.exam.dao.ExamDAO;
import com.blanc.recrute.exam.dto.ExamDTO;

public class ExamService {

  private static ExamDAO examDAO = new ExamDAO();


  public ExamDTO getExamContent(Integer examId) {

    return examId != null ? examDAO.getExamContent(examId) : null;

  }

}
