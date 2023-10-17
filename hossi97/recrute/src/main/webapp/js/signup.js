const id = document.querySelector('input[name="id"]')
const email = document.querySelector('input[name="email"]')
const name = document.querySelector('input[name="name"]')
const gender = document.querySelector('input[name="gender"]')
const password = document.querySelector('input[name="password"]')
const passwordConfirm = document.querySelector('input[name="passwordConfirm"]')
const phoneNumber = document.querySelector('input[name="phoneNumber"]')
const birth = document.querySelector('input[name="birth"]')
const form = document.querySelector("#form")

const validateEmail = (el) => {
    const emailRegex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');
    return emailRegex.test(el.value);
}
const checkInputEmpty = (el) => {
    return (el.value === "" || el.value === null || el.value === undefined)
}

const registerEvent = (e, id, email, name, gender, password, passwordConfirm, phoneNumber, birth) => {
    e.stopPropagation();

    let isProper = false;
    if (checkInputEmpty(id)) {
        alert("아이디를 입력하세요.")
        id.focus()
    } else if (checkInputEmpty(email)) {
        alert("이메일을 입력하세요.")
        email.focus()
    } else if (!validateEmail(email)) {
        alert("이메일 형식이 올바르지 않습니다.")
        email.focus()
    } else if (checkInputEmpty(name)) {
        alert("이름을 입력하세요.")
        name.focus()
    } else if (checkInputEmpty(gender)) {
        alert("성별을 선택하세요.")
        gender.focus()
    } else if (checkInputEmpty(password)) {
        alert("비밀번호를 입력하세요.")
        password.focus()
    } else if (checkInputEmpty(passwordConfirm)) {
        alert("비밀번호 확인을 입력하세요.")
        passwordConfirm.focus()
    } else if (password.value != passwordConfirm.value) {
        alert("비밀번호와 비밀번호 확인 값이 다릅니다.")
        passwordConfirm.focus()
    } else if (checkInputEmpty(phoneNumber)) {
        alert("전화번호를 입력하세요.")
        phoneNumber.focus()
    } else if (checkInputEmpty(birth)) {
        alert("생년월일을 선택하세요.")
        birth.focus()
    } else {
        isProper = true;
    }

    if (!isProper) {
        e.preventDefault();
    }
}

const registerFormSubmitEvent = (id, email, name, gender, password, passwordConfirm, phoneNumber, birth) => {
    form.addEventListener('keydown', (e) => {
        if (e.code === "Enter") {
            registerEvent(e, id, email, name, gender, password, passwordConfirm, phoneNumber, birth)
        }
    }, true);

    form.addEventListener("submit", (e) => {
        registerEvent(e, id, email, name, gender, password, passwordConfirm, phoneNumber, birth)
    });
}

registerFormSubmitEvent(id, email, name, gender, password, passwordConfirm, phoneNumber, birth)
// const loginFormSubmitEvent = (submitBtn, memberId, password) => {
//     form.addEventListener('keydown', (e) => {
//         if (e.code === "Enter") {
//             loginEvent(e, memberId, password)
//         }
//     }, true);
//
//     submitBtn.addEventListener("click", (e) => {
//         loginEvent(e, memberId, password)
//     });
// }

// const loginEvent = (e, memberId, password) => {
//     e.stopPropagation();
//     let isProper = false;
//
//     if (checkInputEmpty(memberId)) {
//         setNotice(memberId)
//     } else if (checkInputEmpty(password)) {
//         setNotice(password)
//     } else {
//         isProper = true;
//     }
//
//     if (isProper) {
//         const memberInfo = {
//             memberId: memberId.value,
//             password: password.value
//         };
//
//         const message = {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             redirect: 'follow',
//             body: JSON.stringify(memberInfo)
//         };
//
//         // redirect 라면 -> not then
//         // error 라면 -> then
//         fetch(window.location.href, message)
//             .then((res) => {
//                 if (res.redirected) {
//                     console.log(res.url)
//                     window.location.href = res.url
//                 } else {
//                     return res.json();
//                 }
//             }).then((json) => {
//             if (json.errorMsg === "Invalid ID/PW") {
//                 formNotice.textContent = "아이디와 비밀번호를 다시 확인해주세요."
//             }
//         })
//     }
// }




// console.log(id.value);
// console.log(email.value);
// console.log(name.value);
// console.log(gender.value);
// console.log(password.value);
// console.log(passwordConfirm.value);
// console.log(phoneNumber.value);
// console.log(birth.value);
