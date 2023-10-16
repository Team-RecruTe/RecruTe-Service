<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
		 
<%@ include file="../layout/header.jsp"%>


<div class="mx-auto max-w-screen-xl px-4 py-16 sm:px-6 lg:px-8">
  <div class="mx-auto max-w-lg text-center">
    <h1 class="text-2xl font-bold sm:text-3xl">🍀로그인🍀</h1>

    <p class="mt-4 text-gray-500">
      Welcome to my world
    </p>
  </div>

  <form action="../member/signin-process" 
  		method="post"
  		class="mx-auto mb-0 mt-8 max-w-md space-y-4">
  		
  	<!-- 아이디 → text -->
    <div>
      <label for="text" class="sr-only">아이디</label>
      <div class="relative">
        <input
	          type="text"
	          name="userId"
	          class="w-full rounded-lg border-gray-200 p-4 pe-12 text-sm shadow-sm"
	          placeholder="아이디를 입력해주세요."/>

        <span class="absolute inset-y-0 end-0 grid place-content-center px-4">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-4 w-4 text-gray-400"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207"
            />
          </svg>
        </span>
      </div>
    </div>

	<!-- 비밀번호 → password -->
    <div>
      <label for="password" class="sr-only">비밀번호</label>
      <div class="relative">
        <input
	          type="password"
	          name="pw"
	          class="w-full rounded-lg border-gray-200 p-4 pe-12 text-sm shadow-sm"
	          placeholder="비밀번호를 입력해주세요."/>


        <span class="absolute inset-y-0 end-0 grid place-content-center px-4">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-4 w-4 text-gray-400"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
            />
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"
            />
          </svg>
        </span>
      </div>
    </div>

    <div class="flex items-center justify-between">
      <p class="text-sm text-gray-500">
        No account?
        <a class="underline" href="">Sign up</a>
      </p>

	<!-- 로그인 버튼 -->
      <button
	        type="submit"
	        class="inline-block rounded-lg bg-blue-500 px-5 py-3 text-sm font-medium text-white"
	      >
        로그인
      </button>
    </div>
  </form>
</div>