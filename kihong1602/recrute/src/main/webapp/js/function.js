function check(word) {
    return !(word === "" || word === null || word === undefined);
}

function invalidEmail(email) {
    const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
    return emailRegex.test(email);
}

function invalid() {
    let memberId = document.querySelector('input[name="member_id"]').value;
    let password = document.querySelector('input[name="password"]').value;
    let name = document.querySelector('input[name="name"]').value;
    let birth = document.querySelector('input[name="birth"]').value;
    let gender = document.querySelector('input[name="gender"]:checked');
    let email = document.querySelector('input[name="email"]').value;
    let tel = document.querySelector('input[name="phoneNumber"]').value;

    let property = true;

    if (!check(memberId)) {
        alert("ID 입력은 필수입니다.");
        document.getElementById('member_id').focus();
        property = false;
    } else if (!check(name)) {
        alert("이름을 입력하세요.");
        document.getElementById('name').focus();
        property = false;
    } else if (!check(email) || !invalidEmail(email)) {
        if (!check(email)) {
            alert("이메일을 입력해주세요.");
        } else if (!invalidEmail(email)) {
            alert("올바르지 않은 이메일 형식입니다.")
        }
        document.getElementById('email').focus();
        property = false;
    } else if (!check(password)) {
        alert("비밀번호 입력은 필수입니다.");
        document.getElementById('password').focus();
        property = false;
    } else if (!check(birth)) {
        alert("생년월일을 입력하세요.");
        document.getElementById('birth').focus();
        property = false;
    } else if (!gender) {
        alert("성별을 골라주세요.");
        property = false;
    } else if (!check(tel)) {
        alert("전화번호를 입력해주세요.");
        document.getElementById('phone_number').focus();
        property = false;
    }

    return property;
}

function loginInvalid() {
    let memberId = document.querySelector('input[name="member_id"]').value;
    let password = document.querySelector('input[name="password"]').value;

    let property = true;
    if (!check(memberId)) {
        alert("아이디를 입력하세요.");
        document.getElementById('member_id').focus();
        property = false;
    } else if (!check(password)) {
        alert("비밀번호를 입력하세요.");
        document.getElementById('password').focus();
        property = false;
    }

    return property;
}

document.getElementById('signup-id').addEventListener('click', function (e) {
    e.preventDefault();
    idDuplicate();
});

function idDuplicate() {
    let memberId = document.querySelector('input[name="member_id"]').value;
    console.log(memberId);
    fetch('/check-id', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({memberId: memberId})
    })
        .then(response => response.text())
        .then(data => {
            const responseData = JSON.parse(data);
            //response.text()를 받아온 것이므로 다시한번 Parsing 을 해주어야 한다.
            if (responseData.data === "available") {
                alert('사용가능한 ID 입니다.');
            } else {
                alert('이미 사용중인 ID 입니다.');
            }
        })
        .catch(error => {
            alert('서버오류가 발생했습니다.');
        })
}

document.getElementById('password_confirm').addEventListener('keyup', function () {
    let password = document.getElementById('password').value;
    let confirm = document.getElementById('password_confirm').value;
    if (password === confirm) {
        document.querySelector('.invalid-feedback').innerHTML = "";
    } else {
        document.querySelector('.invalid-feedback').innerHTML = "비밀번호가 다릅니다.";
    }
})
