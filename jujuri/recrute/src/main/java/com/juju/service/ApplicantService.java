package com.juju.service;

import com.juju.dto.ApplicantDto;
import com.juju.dto.MemberDto;

public interface ApplicantService {
  MemberDto findMember(String userId);

  int insertApplicant(ApplicantDto applicantDto);
}
