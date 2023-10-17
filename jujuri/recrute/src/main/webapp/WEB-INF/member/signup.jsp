<%@ include file="/WEB-INF/include/header.jsp"%>
<!--
  Heads up! 👋

  Plugins:
    - @tailwindcss/forms
-->

<div class="mx-auto max-w-screen-xl px-4 py-16 sm:px-6 lg:px-8">
  <div class="mx-auto max-w-lg text-center">
    <h1 class="text-2xl font-bold sm:text-3xl">Create Account</h1>
  </div>

  <form action="/recrute/signup-process" class="mx-auto mb-0 mt-8 max-w-md space-y-4" method="post">
    <div>
      <label for="id" class="sr-only">ID</label>

      <div class="relative">
        <input
          type="text"
          class="w-full rounded-lg border-gray-200 p-4 pe-12 text-sm shadow-sm" name="userId" id="userId"
          placeholder="Enter your id"
        />
      </div>
    </div>

    <div>
      <label for="password" class="sr-only">Password</label>
      <div class="relative">
        <input
          type="password"
          class="w-full rounded-lg border-gray-200 p-4 pe-12 text-sm shadow-sm" name="pw" id="pw"
          placeholder="Enter your password"
        />
      </div>
    </div>
    
    <div>
      <label for="passwordconfirm" class="sr-only">Password Confirm</label>
      <div class="relative">
        <input
          type="password"
          class="w-full rounded-lg border-gray-200 p-4 pe-12 text-sm shadow-sm" name="pwConfirm" id="pwConfirm"
          placeholder="Password confirm"
        />
      </div>
      <div class="invalid-feedback"></div>
    </div>
	<div>
      <label for="name" class="sr-only">Name</label>
      <div class="relative">
        <input
          type="text"
          class="w-full rounded-lg border-gray-200 p-4 pe-12 text-sm shadow-sm" name="username" id=""username""
          placeholder="Enter your name"
        />
      </div>
    </div>
    <div>
      <label for="birthdate" class="sr-only">BirthDate</label>
      <div class="relative">
        <input
          type="date"
          class="w-full rounded-lg border-gray-200 p-4 pe-12 text-sm shadow-sm" name="birth" id="birth"
          placeholder="Enter your birth date"
        />
      </div>
    </div>
    <div>
      <label for="tel" class="sr-only">PhoneNumber</label>
      <div class="relative">
        <input
          type="tel"
          class="w-full rounded-lg border-gray-200 p-4 pe-12 text-sm shadow-sm" name="phoneNum" id="phoneNum"
          placeholder="01012345678"
        />
      </div>
    </div>
    <!-- <div>
      <label for="gender" class="sr-only">Gender</label>
      <div class="relative">
        <input
          type=text
          class="w-full rounded-lg border-gray-200 p-4 pe-12 text-sm shadow-sm"
          placeholder="Choose your gender"
        />
        
      </div>
    </div> -->
    
    <fieldset class="grid grid-cols-2 gap-4">
  <legend class="sr-only">Delivery</legend>

  <div>
    <input
      type="radio"
      name="gender"
      value="0"
      id="DeliveryStandard"
      class="peer hidden [&:checked_+_label_svg]:block"
      checked
    />

    <label
      for="DeliveryStandard"
      class="block cursor-pointer rounded-lg border border-gray-100 bg-white p-4 text-sm font-medium shadow-sm hover:border-gray-200 peer-checked:border-blue-500 peer-checked:ring-1 peer-checked:ring-blue-500"
    >
      <div class="flex items-center justify-between">
        <p class="text-gray-700">Male</p>

        <svg
          class="hidden h-5 w-5 text-blue-600"
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 20 20"
          fill="currentColor"
        >
          <path
            fill-rule="evenodd"
            d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z"
            clip-rule="evenodd"
          />
        </svg>
      </div>

    </label>
  </div>

  <div>
    <input
      type="radio"
      name="gender"
      value="1"
      id="DeliveryPriority"
      class="peer hidden [&:checked_+_label_svg]:block"
    />

    <label
      for="DeliveryPriority"
      class="block cursor-pointer rounded-lg border border-gray-100 bg-white p-4 text-sm font-medium shadow-sm hover:border-gray-200 peer-checked:border-blue-500 peer-checked:ring-1 peer-checked:ring-blue-500"
    >
      <div class="flex items-center justify-between">
        <p class="text-gray-700">Female</p>

        <svg
          class="hidden h-5 w-5 text-blue-600"
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 20 20"
          fill="currentColor"
        >
          <path
            fill-rule="evenodd"s
            d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z"
            clip-rule="evenodd"
          />
        </svg>
      </div>
    </label>
  </div>
</fieldset>
    
    
    <div>
      <label for="email" class="sr-only">Email</label>

      <div class="relative">
        <input
          type="email"
          class="w-full rounded-lg border-gray-200 p-4 pe-12 text-sm shadow-sm" name="email"
          placeholder="0000@naver.com"
        />

        <!-- <span class="absolute inset-y-0 end-0 grid place-content-center px-4">
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
        </span> -->
      </div>
    </div>
    
    
    <div class="flex items-center justify-between">
      <!-- <p class="text-sm text-gray-500">
        No account?
        <a class="underline" href="">Sign up</a>
      </p> -->

      <button
        type="submit"
        class="inline-block rounded-lg bg-blue-500 px-5 py-3 text-sm font-medium text-white" id="btnSubmit"
      >
        Sign Up
      </button>
    </div>
  </form>
</div>
<script>
$("#pwConfirm").on("keyup", function() {
	if ($("#pw").val() !== $("#pwConfirm").val()) {
		$(".invalid-feedback").show();
		$(".invalid-feedback").text("the password is not equivalent");
	} else {
		$(".invalid-feedback").hide();
		$(".invalid-feedback").text("");
	}

});

/* $("#btnSubmit").on("click", function(e) {
	if ($("#userId").val() === "") {
		e.preventDefault();
		alert("id는 필수입력 사항입니다.");
		//member.userID.value = "";
		//member.userID.focus();
		return false;
	} else if (member.userPW.value.trim() === "") {
		alert("password는 필수입력 사항입니다.");
		member.userPW.value = "";
		member.userPW.focus();
		return false;
	} else if (member.userPW02.value.trim() === "") {
		alert("password확인은 필수입니다.");
		member.userPW02.value = "";
		member.userPW02.focus();
		return false;
	} else if (!isIdCheck) {
		alert("아이디 중복 체크를 해주세요.");
		return false;
	}
}); */
</script>
<%@ include file="/WEB-INF/include/footer.jsp"%>