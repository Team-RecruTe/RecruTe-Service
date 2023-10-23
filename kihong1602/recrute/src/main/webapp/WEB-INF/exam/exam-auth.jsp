<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="../layout/header.jsp" %>
<%--채용 회사 이름--%>
<%--채용 시작일--%>
<%--채용 종료일--%>
<%--응시하기 버튼--%>

<div class="max-w-xl mx-auto bg-white p-6 rounded-lg shadow-lg">
    <h1 class="text-2xl font-bold">${requestScope.examDTO.title}</h1>
    <div class="bg-gray-100 p-4 mt-4 rounded">
        <h2 class="text-lg font-semibold">시험정보</h2>
        <ul class="mt-2">
            <li>채용 시작일: ${requestScope.examDTO.startDate}</li>
            <li>채용 종료일: ${requestScope.examDTO.endDate}</li>
        </ul>
    </div>
    <a
            class="group flex items-center justify-between gap-4 rounded-lg border border-teal-600 bg-teal-600 px-5 py-3 transition-colors hover:bg-transparent focus:outline-none focus:ring"
            href=""
    >
  <span
          class="font-medium text-white transition-colors group-hover:text-teal-600 group-active:text-teal-500"
  >
    응시하기
  </span>

        <span
                class="shrink-0 rounded-full border border-current bg-white p-2 text-teal-600 group-active:text-teal-500"
        >
    <svg
            class="h-5 w-5 rtl:rotate-180"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
    >
      <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M17 8l4 4m0 0l-4 4m4-4H3"
      />
    </svg>
  </span>
    </a>
</div>
<%@include file="../layout/footer.jsp" %>
