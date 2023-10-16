<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
		 
<%@ include file="../layout/header.jsp"%>

<div>
  <h3>🍀회원가입🍀</h3>
        <form action="../member/signup-process" 
        	  method="post" >

                <table border="1" width="400" height="400">
                		
                	   <!-- 아이디 → text -->
                       <tr>
							<td align="center" bgcolor="#FFFFCC">아이디</td>
						    <td>
							    <input 
						    			type="text" 
						    			name="userId"/>
						    </td>                                
                       </tr>
                  
                       
                       <!-- 비밀번호 → password -->
                       <tr>
							<td align="center" bgcolor="#FFFFCC">비밀번호</td>
							<td><input type="password" name="pw" /></td>
                       </tr>
                       
                       <!-- 비밀번호 확인 → password -->
                       <tr>
							<td align="center" bgcolor="#FFFFCC">비밀번호 확인</td>
							<td><input type="password" name="pwConfirm" /></td>
                       </tr>
                       
                       <!-- 이름 → text -->
                       <tr>
							<td align="center" bgcolor="#FFFFCC">이름</td>
							<td><input type="text" name="username"/></td>
                       </tr>

						<!-- 생년월일 → date -->
                       <tr>
                            <td align="center" bgcolor="#FFFFCC">생년월일</td>
                            <td><input type="date" name="birth"/></td>
                       </tr>
						
						<!-- 성별 → radio -->
						<tr>
                            <td align="center" bgcolor="#FFFFCC">성별</td>
                            <td>
                               <input type="radio" id="ag1" name="gender" checked="checked" />
                               <label name="gender" for="ag1">남자</label>
                               <input type="radio" id="ag2" name="gender" />
                               <label name="gender" for="ag2">여자</label>
                            </td>
                       </tr>
                       
                       
                       <!-- 전화번호 → tel -->
                       <tr>
                            <td align="center" bgcolor="#FFFFCC">전화번호</td>
                            <td><input type="tel" name="phoneNum"/></td>
                       </tr>
                       
                       <!-- 이메일 → email -->
                       <tr>
                            <td align="center" bgcolor="#FFFFCC">이메일</td>
                            <td><input type="email" name="email"/></td>
                       </tr>        

                </table>
                
				                
                      

                <br />
                <div class="">
					<button type="submit" >회원가입</button>
					<button type="reset" >취소</button>
				</div>
        </form>

</div>

