<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

		 
		 
<%@ include file="../layout/header.jsp"%>

<div class="overflow-x-auto">
  <table class="min-w-full divide-y-2 divide-gray-200 bg-white text-sm">
    <thead class="ltr:text-left rtl:text-right">
      <tr>
        <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
          글번호
        </th>
        <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
          회사고유번호
        </th>
        <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
          회사명
        </th>
        <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
          채용타이틀
        </th>
        <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
          직무
        </th>
        <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
          연봉
        </th>
      </tr>
    </thead>

    <tbody class="divide-y divide-gray-200">
    <c:forEach items="${compRecruitList}" var="list" varStatus="status">
      <tr class="odd:bg-gray-50">
      
    	<td class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
        	  ${status.index + 1}
        </td>
      	<td class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
        	  ${list.id_comIndex}
        </td>
        <td class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
        	  ${list.company_name}
        </td>
        <td class="whitespace-nowrap px-4 py-2 text-gray-700">
	        <a href="../compRecruit/view?id_recruitIndex=${list.id_recruitIndex}">
	        ${list.title}
	        </a>
        </td>
        <td class="whitespace-nowrap px-4 py-2 text-gray-700">
      	 	 ${list.job}
        </td>
        <td class="whitespace-nowrap px-4 py-2 text-gray-700">
      	 	 ${list.salary}
        </td>
      </tr>
    </c:forEach>

  </table>
</div>








<%@ include file="../layout/footer.jsp"%>