package com.hossi.recrute.exam.dao;

import com.hossi.recrute.exam.dto.SendExamEmailDto;

import java.util.List;

public interface ExamMapper {
    List<SendExamEmailDto> selectUserEmailAndAptIdAndCompanyName(Integer recruitmentId);
}
