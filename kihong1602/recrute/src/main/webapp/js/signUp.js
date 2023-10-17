let memberId = document.querySelector('input[name="member_id"]');
let password = document.querySelector('input[name="password"]');
let name = document.querySelector('input[name="name"]');
let birth = document.querySelector('input[name="birth"]');
let gender = document.querySelector('input[name="gender"]');
let email = document.querySelector('input[name="email"]');
let tel = document.querySelector('input[name="phoneNumber"]');
let form = document.getElementById('signup-form');

let isProperId = false;


function check(word) {
    return (word.value === "" || word.value === null || word.value === undefined);
}

function invalidEmail(email) {
    const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
    return emailRegex.test(email.value);
}

function invalid(e, memberId, password, name, birth, gender, email, tel) {
    e.stopPropagation();

    let isProper = false;
    if (check(memberId)) {
        alert("ID 입력은 필수입니다.");
        memberId.focus();
    } else if (!isProperId) {
        alert("중복된 아이디 입니다.");
        memberId.focus();
    } else if (check(email) || !invalidEmail(email)) {
        if (check(email)) {
            alert("이메일을 입력해주세요.");
        } else if (!invalidEmail(email)) {
            alert("올바르지 않은 이메일 형식입니다.")
        }
        email.focus();
    } else if (check(password)) {
        alert("비밀번호 입력은 필수입니다.");
        password.focus();
    } else if (check(birth)) {
        alert("생년월일을 입력하세요.");
        birth.focus();
    } else if (check(gender)) {
        alert("성별을 골라주세요.");
    } else if (check(tel)) {
        alert("전화번호를 입력해주세요.");
        tel.focus();
    } else {
        isProper = true;
    }

    if (!isProper) {
        e.preventDefault();
    }
}

const signUpFormSubmitEvent = (memberId, password, name, birth, gender, email, tel) => {
    form.addEventListener('keydown', (e) => {
        if (e.code === "Enter") {
            invalid(e, memberId, password, name, birth, gender, email, tel)
        }
    }, true);

    form.addEventListener('submit', (e) => {
        invalid(e, memberId, password, name, birth, gender, email, tel)

    });
}

signUpFormSubmitEvent(memberId, password, name, birth, gender, email, tel);

document.getElementById('signup-id').addEventListener('click', function (e) {
    e.preventDefault();
    idDuplicate();
});

function idDuplicate() {
    let idValue = memberId.value;

    fetch('/check-id', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({memberId: idValue})
    })
        .then(response => response.text())
        .then(data => {
            const responseData = JSON.parse(data);
            //response.text()를 받아온 것이므로 다시한번 Parsing 을 해주어야 한다.
            if (responseData.data === "available") {
                alert('사용가능한 ID 입니다.');
                isProperId = true;
            } else {
                alert('이미 사용중인 ID 입니다.');
            }
        })
        .catch(error => {
            alert('서버오류가 발생했습니다.');
        })
}

document.getElementById('password_confirm').addEventListener('keyup', function () {
    let psValue = password.value;
    let confirm = document.getElementById('password_confirm').value;
    let invalidFeedback = document.querySelector('.invalid-feedback');
    if (psValue === confirm) {
        invalidFeedback.innerHTML = "";
    } else {
        invalidFeedback.innerHTML = "비밀번호가 다릅니다.";
    }
})