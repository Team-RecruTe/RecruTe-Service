<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<div class="mx-auto max-w-screen-xl px-4 py-16 sm:px-6 lg:px-8">
    <div class="mx-auto max-w-lg">
        <h1 class="text-center text-2xl font-bold text-indigo-600 sm:text-3xl">
            Get started today
        </h1>

        <p class="mx-auto mt-4 max-w-md text-center text-gray-500">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Obcaecati sunt
            dolores deleniti inventore quaerat mollitia?
        </p>

        <form
                id="form"
                class="mb-0 mt-6 space-y-4 rounded-lg p-4 shadow-lg sm:p-6 lg:p-8"
        >
            <p class="text-center text-lg font-medium">Input your ApplicantId</p>

            <div class="">
<%--                <label for="password">Applicant ID</label>--%>
                <div class="relative w-full">
                    <input id="password" name="password" type="password" class="w-full rounded-lg border-gray-200 p-4 pe-4 text-sm shadow-sm" placeholder="Enter password">
                    <span class="absolute inset-y-0 end-0 grid place-content-center px-4">
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"></path>
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"></path>
                    </svg>
                  </span>
                </div>
            </div>

            <button
                    id="submit-btn"
                    type="button"
                    class="block w-full rounded-lg bg-indigo-600 px-5 py-3 text-sm font-medium text-white"
            >
                Go To Exam
            </button>
        </form>
    </div>
</div>
<script src="/js/exam.js"></script>
