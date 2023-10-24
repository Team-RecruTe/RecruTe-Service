package com.blanc.recrute.exam.service;

import com.blanc.recrute.exam.dao.ExamDAO;
import com.blanc.recrute.exam.dto.ExamInfoDTO;
import com.blanc.recrute.exam.dto.ExaminationDTO;
import com.blanc.recrute.exam.dto.answer.AnswerData;
import java.util.List;

public class ExamService {

  private static ExamDAO examDAO = new ExamDAO();

  public ExamInfoDTO getExamContent(Integer recruitId) {

    return recruitId != null ? examDAO.getExamContent(recruitId) : null;

  }

  public List<ExaminationDTO> getExamination(Integer examId) {
    Integer recruitId = examDAO.getRecruitId(examId);

    return examDAO.getExamination(recruitId);
  }

  public String saveExamination(AnswerData answerData) {
    if (examDAO.saveExamination(answerData) != null) {
      return "success";
    }
    return "fail";
  }
}
