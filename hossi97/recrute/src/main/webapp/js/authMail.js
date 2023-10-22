const authBtn = document.querySelector("#auth-btn")

const authMail = () => {
    const path = `/signup/complete`
    const msg = {
        method: 'POST'
    }
    fetch(path, msg)
        .then(res => res.json())
        .then(data => {
            console.log("굿")
        });
}

authBtn.addEventListener("mousedown", authMailEvent = () => {
    console.log("클릭")
    authMail()
})