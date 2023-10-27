<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

		 
		 
<%@ include file="../layout/header.jsp"%>


<form action="../applicant/applicant-process" 
      method="post">

	<!-- 히든설정 -->
	<!-- from 세션 -->
	<input type="hidden"
		   id="loggedMemberId"
		   name="loggedMemberId"
		   value="${loggedMemberId}"> 
		   
	<input type="hidden"
		   id="loggedEmail"
		   name="loggedEmail"
		   value="${loggedEmail}"> 
		   
   	<input type="hidden"
		   id="loggedId"
		   name="loggedId"
		   value="${loggedId}"> 
	
	<!-- from dao(compRecruitView) -->
	<input type="hidden"
		   id="id_recruitIndex"
		   name="id_recruitIndex"
		   value="${compRecruitView.id_recruitIndex}"> 
	
	<input type="hidden"
		   id="company_name"
		   name="company_name"
		   value="${compRecruitView.company_name}"> 

	
	<div>
	  <div class="px-4 sm:px-0">
	    <h3 class="text-base font-semibold leading-7 text-gray-900">
	    💗 ${compRecruitView.company_name} 💗채용공고입니다.
	    </h3>
	  </div>
	  <div class="mt-6 border-t border-gray-100">
	    <dl class="divide-y divide-gray-100">
	      <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
	        <dt class="text-sm font-medium leading-6 text-gray-900">
	        제목
	        </dt>
	        <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0">
	        ${compRecruitView.title}
	        </dd>
	      </div>
	      <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
	        <dt class="text-sm font-medium leading-6 text-gray-900">
	        지원시작날짜
	        </dt>
	        <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0">
	        ${compRecruitView.start_date}
	        </dd>
	      </div>
	      <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
	        <dt class="text-sm font-medium leading-6 text-gray-900">
	        지원종료날짜
	        </dt>
	        <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0">
	        ${compRecruitView.end_date}
	        </dd>
	      </div>
	      <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
	        <dt class="text-sm font-medium leading-6 text-gray-900">급여</dt>
	        <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0">
	        ${compRecruitView.salary}
	        </dd>
	      </div>
	      <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
	        <dt class="text-sm font-medium leading-6 text-gray-900">
	        근무지역
	        </dt>
	        <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0">
	        ${compRecruitView.location}
	        </dd>
	      </div>
	      <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
	        <dt class="text-sm font-medium leading-6 text-gray-900">
	        근무형태
	        </dt>
	        <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0">
	        ${compRecruitView.work_form}
	        </dd>
	      </div>
	      <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
	        <dt class="text-sm font-medium leading-6 text-gray-900">
	        직무
	        </dt>
	        <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0">
	        ${compRecruitView.job}
	        </dd>
	      </div>
	      <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
	        <dt class="text-sm font-medium leading-6 text-gray-900">
	        요구 스킬
	        </dt>
	        <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0">
	        ${compRecruitView.skills}
	        </dd>
	      </div>
	      <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
	        <dt class="text-sm font-medium leading-6 text-gray-900">
	        요구 경력
	        </dt>
	        <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0">
	        ${compRecruitView.career}
			</dd>
	      </div>
	    </dl>
	  </div>
	</div>
	
	
	<!-- 지원하기 버튼 -->
	<div class="mt-4">
		<button
			    type="submit"
			    id="btnApplicant"
			    class="inline-block w-full rounded-lg bg-black px-5 py-3 font-medium text-white sm:w-auto">
	    지원하러 가기👻
	  </button>
	</div>
</form>

<%@ include file="../layout/footer.jsp"%>


<script>
// 로그인한 아이디가 있을 경우 > 알림창 > 확인 > 데이터를 insert
//								 취소 > 알림창 끄기
// 로그인한 아이디가 없을 경우 > 알림창 : "로그인 후 지원가능합니다."

const loggedId = $("#loggedId").val();

$("#btnApplicant").on("click",function(e){
	
	console.log($("#loggedEmail").val());
	 if(loggedId === null || loggedId.trim()===""){
		 alert("로그인 후 지원하실 수 있습니다.");
	 	 return false;
	 } 
}) 




</script>





