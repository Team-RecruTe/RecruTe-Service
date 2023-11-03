<%--
  Created by IntelliJ IDEA.
  User: kks45
  Date: 2023-10-16
  Time: 오전 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<main>
    <section class="bg-gray-50">
        <div
                class="mx-auto max-w-screen-xl px-4 py-32 lg:flex lg:h-screen lg:items-center"
        >
            <div class="mx-auto max-w-xl text-center">
                <h1 class="text-3xl font-extrabold sm:text-5xl">
                    만나서 반가워요!
                    <strong class="font-extrabold text-red-700 sm:block">
                        처음오셨나요?
                    </strong>
                </h1>

                <p class="mt-4 sm:text-xl/relaxed">
                    Start를 눌러 우리와 함께 시작해요!
                </p>

                <div class="mt-8 flex flex-wrap justify-center gap-4">
                    <a
                            class="block w-full rounded bg-red-600 px-12 py-3 text-sm font-medium text-white shadow hover:bg-red-700 focus:outline-none focus:ring active:bg-red-500 sm:w-auto"
                            href="/signup"
                    >
                        Start
                    </a>

                    <a
                            class="block w-full rounded px-12 py-3 text-sm font-medium text-red-600 shadow hover:text-red-700 focus:outline-none focus:ring active:text-red-500 sm:w-auto"
                            href="/about"
                    >
                        Learn More
                    </a>
                </div>
            </div>
        </div>
    </section>
</main>
