const insertNotice = document.querySelector("#insertNotice");
const boardTitle = document.querySelector("#boardTitle");
const boardContent = document.querySelector("#boardContent");

insertNotice.addEventListener("submit", e => {

  const inputTitle = boardTitle.value;
  const inputContent = boardContent.value;

  const obj = {
    "boardTitle" : inputTitle,
    "boardContent" : inputContent,
    "codeNo" : codeNo,
    "memberNo" : memberNo
  }

  console.log(inputTitle);
  console.log(inputContent);
  console.log(codeNo);
  console.log(memberNo);

  fetch("/community/insertNotice", {
    method : "POST",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(obj)
  })
  .then(resp => resp.text())
  .then(result => {

    if(result == 0) {
      alert("공지사항 등록 실패");
      return;
    }
    
    alert("공지사항 등록이 완료되었습니다.");
    location.href = "/community/noticeList";

  })

})