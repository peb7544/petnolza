const backToList = document.querySelector("#backToList");
const updateNotice = document.querySelector("#updateNotice");
const deleteNotice = document.querySelector("#deleteNotice");



backToList.addEventListener("click", () => {

  window.location.href = "http://localhost/community/noticeList";

})



deleteNotice.addEventListener("click", () => {

  if( !confirm("해당 공지사항 게시물을 삭제하시겠습니까?") ) {
    alert("공지사항 삭제가 취소되었습니다.");
    return;
  }

  location.href = "/community/deleteNotice?boardNo=" + boardNo;

});


