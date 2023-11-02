<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <style>
        .text {
            font-size: 24px;
            font-weight: bold;
            color: white; /* 텍스트 색상 변경 가능 */
        }

        .description {
            font-size: 18px;
            color: white; /* 설명 텍스트 색상 변경 가능 */
        }
    </style>
</head>

<section class="bg-gray-900 text-white">
    <div
            class="mx-auto max-w-screen-xl px-4 py-32 lg:flex lg:h-screen lg:items-center"
    >
        <div class="mx-auto max-w-3xl text-center">
            <h1
                    class="bg-gradient-to-r from-green-300 via-blue-500 to-purple-600 bg-clip-text text-3xl font-extrabold text-transparent sm:text-5xl"
            >
                RecruTe

                <span class="sm:block mt-4"> 최고의 채용 파트너 </span>
            </h1>

            <p class="mx-auto mt-4 max-w-xl sm:text-xl/relaxed split">
                <span class="text">우수한 인재를 찾고 싶으세요? 여기서 시작하세요.</span><br/>
            </p>

            <div class="mt-8 flex flex-wrap justify-center gap-4">
                <a
                        class="block w-full rounded border border-blue-600 bg-blue-600 px-12 py-3 text-sm font-medium text-white hover:bg-transparent hover:text-white focus:outline-none focus:ring active:text-opacity-75 sm:w-auto"
                        href="/get-started"
                >
                    Get Started
                </a>

                <a
                        class="block w-full rounded border border-blue-600 px-12 py-3 text-sm font-medium text-white hover:bg-blue-600 focus:outline-none focus:ring active:bg-blue-500 sm:w-auto"
                        href="/about"
                >
                    Learn More
                </a>
            </div>
        </div>
    </div>
</section>