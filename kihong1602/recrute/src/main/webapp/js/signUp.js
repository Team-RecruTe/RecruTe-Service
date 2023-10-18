let memberId = document.querySelector('input[name="member_id"]');
let password = document.querySelector('input[name="password"]');
let confirm = document.getElementById('password_confirm');
let name = document.querySelector('input[name="name"]');
let birth = document.querySelector('input[name="birth"]');
let gender = document.querySelector('input[name="gender"]');
let email = document.querySelector('input[name="email"]');
let tel = document.querySelector('input[name="phoneNumber"]');
let invalidFeedback = document.querySelector('.invalid-feedback');
let form = document.getElementById('signup-form');

let isProperId = false;

function invalid(e, memberId, password, name, birth, gender, email, tel) {
    e.stopPropagation();

    let isProper = false;
    
    if (check(memberId)) {
        alert("ID 입력은 필수입니다.");
        memberId.focus();
    } else if (!isProperId) {
        alert("중복된 아이디 입니다.");
        memberId.focus();
    } else if (invalidId(memberId)) {
        alert("올바르지 않은 아이디 형식입니다.");
        memberId.focus();
    } else if (check(name)) {
        alert("이름을 입력해주세요.");
        name.focus();
    } else if (invalidName(name)) {
        alert("올바르지않은 이름 형식입니다.");
        name.focus();
    } else if (check(email)) {
        alert("이메일을 입력해주세요.");
        email.focus();
    } else if (!invalidEmail(email)) {
        alert("올바르지 않은 이메일 형식입니다.")
        email.focus();
    } else if (check(password)) {
        alert("비밀번호 입력은 필수입니다.");
        password.focus();
    } else if (invalidPw(password)) {
        alert("올바르지 않은 비밀번호 형식입니다.");
        password.focus();
    } else if (check(birth)) {
        alert("생년월일을 입력하세요.");
        birth.focus();
    } else if (check(gender)) {
        alert("성별을 골라주세요.");
    } else if (check(tel)) {
        alert("전화번호를 입력해주세요.");
        tel.focus();
    } else if (invalidTel(tel)) {
        alert("올바르지 않은 전화번호 형식입니다.");
        tel.focus();
    } else {
        isProper = true;
    }

    if (!isProper) {
        e.preventDefault();
    }
}

function check(word) {
    return (word.value === "" || word.value === null || word.value === undefined);
}

function invalidId(memberId) {
    const idRegex = /^(?=.*[a-zA-Z])(?=.*[0-9]).{1,30}$/;
    return idRegex.test(memberId.value);
}


function invalidPw(password) {
    const pwRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,30}$/;
    return pwRegex.text(password.value);
}

function invalidName(name) {
    const nameRegex = /^(?=.*[a-zA-Z])(?=.*[ㄱ-ㅎ가-힣]).{1,30}$/;
    return nameRegex.test(name.value);
}

function invalidEmail(email) {
    const emailRegex = /^[a-z0-9]+@[a-z]+\.[a-z.]{2,}$/;
    return emailRegex.test(email.value);
}

function invalidTel(tel) {
    const telRegex = /^(?=.*[0-9]).{10,20}$/;
    return telRegex.text(tel.value);
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

    fetch('/check-id', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({memberId: memberId.value})
    })
        .then(response => response.text())
        .then(data => {
            //response.text()를 받아온 것이므로 다시한번 Parsing 을 해주어야 한다.
            if (JSON.parse(data).data === "available") {
                alert('사용가능한 ID 입니다.');
                isProperId = true;
            } else if (JSON.parse(data).data === "unavailable") {
                alert('이미 사용중인 ID 입니다.');
            } else {
                alert('입력된 아이디가 없습니다. 아이디를 적어주세요.')
            }
        })
        .catch(error => {
            alert('서버오류가 발생했습니다.');
        })
}

confirm.addEventListener('keyup', function () {
    invalidFeedback.innerHTML = password.value === confirm.value ? "" : "비밀번호가 다릅니다.";
})