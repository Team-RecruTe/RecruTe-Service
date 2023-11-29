const authBtn = document.querySelector("#auth-btn")

const authMail = () => {
    const path = `/member/email`
    const msg = {
        method: 'POST'
    }
    fetch(path, msg)
        .then(res => res.json())
        .then(data => {
            console.log(data)
        });
}

authBtn.addEventListener("mousedown", authMailEvent = () => {
    alert("메일을 전송하였습니다.")
    authMail()
})