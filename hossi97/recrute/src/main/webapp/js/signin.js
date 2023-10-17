const id = document.querySelector('input[name="id"]')
const password = document.querySelector('input[name="password"]')
const submitBtn = document.querySelector('#submit-btn')

const checkInputEmpty = (el) => {
    return (el.value === "" || el.value === null || el.value === undefined)
}

const isNotEmpty = (id, password) => {
    if (checkInputEmpty(id)) {
        alert("아이디를 입력해주세요.")
        id.focus()
        return false
    } else if (checkInputEmpty(password)) {
        alert("비밀번호를 입력해주세요.")
        password.focus()
        return false
    }

    return true;
}

const sendData = (idVal, passwordVal) => {
    const info = {
        memberId: idVal,
        password: passwordVal
    }

    const msg = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        redirect: 'follow',
        body: JSON.stringify(info)
    }

    fetch(window.location.href, msg)
        .then((res) => {
            if (res.redirected) {
                window.location.href = res.url
            } else {
                return res.json();
            }
        }).then((json) => {
            if (json.code === "USR-04") {
                alert("아이디와 비밀번호를 다시 확인해주세요.")
            }
    })
}

const checkAndSend = (e, id, password) => {
    e.stopPropagation();

    if (isNotEmpty(id, password)) {
        sendData(id.value, password.value)
    }
}

const addFormEvent = (id, password) => {
    form.addEventListener('keydown', (e) => {
        if (e.code === "Enter") {
            checkAndSend(e, id, password)
        }
    }, true);
}
const addBtnEvent = (id, password) => {
    submitBtn.addEventListener("click", (e) => {
        checkAndSend(e, id, password)
    });
}

const addSubmitEvent = (id, password) => {
    addFormEvent(id, password)
    addBtnEvent(id, password)
}

addSubmitEvent(id, password)