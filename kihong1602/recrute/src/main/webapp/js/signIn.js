let memberId = document.querySelector('input[name="member_id"]');
let password = document.querySelector('input[name="password"]');
let form = document.getElementById('form');

function check(word) {
    return (word.value === "" || word.value === null || word.value === undefined);
}

function invalid(e, memberId, password) {
    let isProper = false;

    if (check(memberId)) {
        alert("ID 입력은 필수입니다.");
        memberId.focus();
    } else if (check(password)) {
        alert("비밀번호 입력은 필수입니다.");
        password.focus();
    } else {
        isProper = true;
    }

    if (!isProper) {
        e.preventDefault();
    }
}


const signInFormSubmitEvent = (memberId, password) => {
    form.addEventListener('keydown', (e) => {
        if (e.code === "ENTER") {
            invalid(e, memberId, password);
        }
    }, true);

    form.addEventListener('submit', (e) => {
        invalid(e, memberId, password);
    });
}

signInFormSubmitEvent(memberId, password);