const backToList = document.querySelector("#backToList");
const updateNotice = document.querySelector("#updateNotice");
const deleteNotice = document.querySelector("#deleteNotice");

const boardTitle = document.querySelector("#boardTitle");
const boardContent = document.querySelector("#boardContent");


backToList.addEventListener("click", () => {

  window.location.href = "http://localhost/community/noticeList";

})


updateNotice.addEventListener("click", e => {
  
  const inputTitle = boardTitle.value;
  const inputContent = boardContent.value;
  
  const obj = {
    "boardTitle" : inputTitle,
    "boardContent" : inputContent,
    "boardNo" : boardNo
  }
  /*
  console.log(inputTitle);
  console.log(inputContent);
  console.log(boardNo);
  */
  fetch("/community/updateNotice", {
    method : "POST",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(obj)
  })
  .then(resp => resp.text())
  .then(result => {


    if(result == 0) {
      alert("공지사항 수정에 실패하였습니다");
      return;
    }

    alert("공지사항 변경이 완료되었습니다");

  })
  .catch(err => console.log(err));

});

deleteNotice.addEventListener("click", () => {

  location.href = "/community/deleteNotice?boardNo=" + boardNo;

});
