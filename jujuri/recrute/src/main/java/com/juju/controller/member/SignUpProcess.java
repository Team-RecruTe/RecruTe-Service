package com.juju.controller.member;

import java.io.IOException;
import java.time.LocalDate;
import com.juju.dao.MemberDao;
import com.juju.dto.MemberDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignUpProcess extends HttpServlet {
  private static final long serialVersionUID = 1L;


  public SignUpProcess() {
    super();

  }


  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String userId = request.getParameter("userId");
    String pw = request.getParameter("pw");
    String pwConfirm = request.getParameter("pwConfirm");
    String username = request.getParameter("username");
    LocalDate birth = LocalDate.parse(request.getParameter("birth"));
    String phoneNum = request.getParameter("phoneNum");
    int gender = Integer.parseInt(request.getParameter("gender"));
    String email = request.getParameter("email");

    System.out.println(gender);

    // 여기서 멤버디티오 생성해준 후 파라미터로 받아온 값 설정해주기 -> memberDao 만들어서 insertMember메소드 호출

    MemberDto memberDto = new MemberDto();
    memberDto.setMember_id(userId);
    memberDto.setPassword(pw);
    memberDto.setName(username);
    memberDto.setBirth(birth);
    memberDto.setPhone_number(phoneNum);
    memberDto.setEmail(email);
    memberDto.setGender(gender);

    MemberDao memberDao = new MemberDao();
    int result = memberDao.insertMember(memberDto);
    if (result > 0) {
      System.out.println("회원가입 성공");
    } else {
      System.out.println("회원가입 실패");
    }
  }
}
