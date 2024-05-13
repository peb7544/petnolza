const cancelRegist = document.querySelector("#cancelRegist");
const insertNotice = document.querySelector("#insertNotice");
const boardTitle = document.querySelector("#boardTitle");
const boardContent = document.querySelector("#boardContent");


insertNotice.addEventListener("submit", e => {

  const inputTitle = boardTitle.value;
  const inputContent = boardContent.value;

  if(inputTitle.trim().length === 0) {
    alert("공지사항 제목을 입력해주세요");
    boardTitle.focus();
    e.preventDefault();
    return;
  }

  if(inputContent.trim().length === 0) {
    alert("공지사항 내용을 입력해주세요.");
    boardContent.focus();
    e.preventDefault();
    return;
  }

});




cancelRegist.addEventListener("click", () => {

  location.href="/community/noticeList";

})