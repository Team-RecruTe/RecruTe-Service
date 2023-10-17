package com.juju.service;

import com.juju.dto.MemberDto;

public interface MemberService {
  int insertMember(MemberDto memberDto);

  MemberDto loginMember(MemberDto memberDto);

}
