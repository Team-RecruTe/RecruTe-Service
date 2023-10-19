<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
		 
<%@ include file="../layout/header.jsp"%>



<!--
  Heads up! 👋

  Plugins:
    - @tailwindcss/forms
-->

<section class="bg-gray-100">
  <div class="mx-auto max-w-screen-xl px-4 py-16 sm:px-6 lg:px-8">
    <div class="grid grid-cols-1 gap-x-16 gap-y-8 lg:grid-cols-1">
      <div class="rounded-lg bg-white p-8 shadow-lg lg:col-span-3 lg:p-12">
      
      <h3>🍀회원가입🍀</h3>
        <form class="space-y-4"
        	  action="../member/signup-process" 
        	  method="post" >

        
          <!-- 아이디 -->
          <div>
            <label class="sr-only" for="name">아이디</label>
            <input
	              class="w-full rounded-lg border-gray-200 p-3 text-sm"
	              placeholder="아이디"
	              type="text"
	              name="userId"
	              id="userId"/>
          </div>
          
          <!-- 비밀번호 -->
          <div>
            <label class="sr-only" for="name">비밀번호</label>
            <input
	              class="w-full rounded-lg border-gray-200 p-3 text-sm"
	              placeholder="비밀번호"
	              type="password"
	              name="pw"
	              id="pw"/>
          </div>
          
          <!-- 비밀번호 확인 -->
          <div>
            <label class="sr-only" for="name">비밀번호 확인</label>
            <input
	              class="w-full rounded-lg border-gray-200 p-3 text-sm"
	              placeholder="비밀번호"
	              type="password"
	              name="pwConfirm"
	              id="pwConfirm"/>
          </div>
          
          <!-- 이름 -->
          <div>
            <label class="sr-only" for="username">이름</label>
            <input
              class="w-full rounded-lg border-gray-200 p-3 text-sm"
              placeholder="이름"
              type="text"
              name="username"
              id="username"/>
          </div>
          
          <!-- 생년월일 -->
          <div>
            <label class="sr-only" for="name">생년월일</label>
            <input
              class="w-full rounded-lg border-gray-200 p-3 text-sm"
              type="date"
              name="birth"
              id="birth"/>
          </div>




		  <!-- 성별 -->
		
		 <div >
            <div>
               <input type="radio" id="ag1" name="gender" checked="checked" value="0" />
               <label name="gender" for="ag1">남자</label>
               <input type="radio" id="ag2" name="gender" value="1" />
               <label name="gender" for="ag2">여자</label>
            </div>
          </div>
		
		          
          
          
          <!-- 전화번호 -->
          <div>
              <label class="sr-only" for="phone">전화번호</label>
              <input
                class="w-full rounded-lg border-gray-200 p-3 text-sm"
                placeholder="전화번호"
                type="tel"
                name="phoneNum"
                id="phoneNum"
              />
            </div>
            
            
            <!-- 이메일 -->
            <div class="relative">
			  <label for="UserEmail" class="sr-only"> 이메일 </label>
			
			  <input
			  	class="w-full rounded-lg border-gray-200 p-3 text-sm"
			    placeholder="flea@rhcp.com"
			    type="email"
			    name="email"
			    id="email"
			  />
			
			</div>
			
			

		  <!-- 버튼 -->
          <div class="mt-4">
            <button
              type="submit"
              class="inline-block w-full rounded-lg bg-black px-5 py-3 font-medium text-white sm:w-auto">
              회원가입
            </button>
            <button
              type="reset"
              class="inline-block w-full rounded-lg bg-black px-5 py-3 font-medium text-white sm:w-auto">
              취소
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</section>



<%@ include file="../layout/footer.jsp"%>
