function sendExaminationEmail() {
  let aptId = document.getElementById('apt-id').value;
  let examId = parsingURL();
  fetch(`/exam/${examId}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      aptId: `${aptId}`
    })
  })
  .then(response => response.text())
  .then(data => {
    let result = JSON.parse(data).data;
    //수험번호가 동일할 때
    //수험번호가 동일하지 않을 때
  })
}

function parsingURL() {
  const correntURL = window.location.href;
  const splitURL = correntURL.split("/");
  return splitURL[splitURL.length - 1];
}