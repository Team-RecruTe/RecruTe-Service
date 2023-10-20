package com.jisun.dao;

import org.apache.ibatis.session.SqlSession;


import com.jisun.dto.MemberDto;
import com.jisun.mybatis.MybatisConnectionFactory;

public class MemberDao {
	
    /**
     * 사용자 회원가입 시 아이디 중복을 확인하는 메서드
     * 
     * @param userId 아이디 중복 확인시 
     * @return 있으면1 없으면0
     */
	public int idCheck (String userId) {
		
		// 초기화
		int result = 0;
		
		// MyBatis의 SqlSession을 가져오는 메서드를 호출하여 SqlSession 객체를 획득
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		
		// MyBatis 매퍼 파일에 정의된 "result" 쿼리를 실행하고 결과를 받아옴
		// sqlSession.selectOne("xml의 id="~"",param);
		result = sqlSession.selectOne("idCheck",userId); //0 or 1
				 // 사용이 끝난 SqlSession 객체를 닫음
		         sqlSession.close();
		 
		// 함수가 끝나도 종료되지 않고 값을 돌려주어야 함
		return result;
		
	}

	
	
	
	
	
	
	
	
    /**
     * 사용자 회원가입을 처리하는 메서드
     * 
     * @param memberDto 회원가입을 요청 시 전달되는 MemberDto 객체
     */
	public int insertMember(MemberDto memberDto) {
		// 초기화
		int result = 0;
		// MyBatis의 SqlSession을 가져오는 메서드를 호출하여 SqlSession 객체를 획득
        SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
        // MyBatis 매퍼 파일에 정의된 id="insertMember" 쿼리를 실행하고 결과를 받아옴
        result = sqlSession.insert("insertMember", memberDto); 
        // 사용이 끝난 SqlSession 객체를 닫음
        sqlSession.close();
        // 
        return result;
	}
	
	
	
    /**
     * 사용자 로그인을 처리하는 메서드
     * 
     * @param memberDto 로그인 요청 시 전달되는 MemberDto 객체
     * @return 로그인된 Member 정보(MemberDto) 또는 null(로그인 실패 시)
     */
	public MemberDto loginMember(MemberDto memberDto) {
		// 초기화
		MemberDto loggedMember = null;
        // MyBatis의 SqlSession을 가져오는 메서드를 호출하여 SqlSession 객체를 획득
        SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
        // MyBatis 매퍼 파일에 정의된 id="loginMember" 쿼리를 실행하고 결과를 받아옴
        loggedMember = sqlSession.selectOne("loginMember", memberDto); //selectOne : 결과값이 하나의 row일때
        // 사용이 끝난 SqlSession 객체를 닫음
        sqlSession.close();
        // 로그인된 Member 정보 또는 null을 반환
        return loggedMember;
	}

}
