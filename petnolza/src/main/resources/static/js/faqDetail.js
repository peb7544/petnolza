const backToList = document.querySelector("#backToList");
const deleteBtn = document.querySelector("#deleteBtn");

backToList.addEventListener("click", () => {

    window.location.href = "http://localhost/community/faqList";

})


deleteBtn.addEventListener("click", () => {

    if( !confirm("해당 자주 묻는 질문을 삭제하시겠습니까?") ) {
        alert("자주 묻는 질문 삭제가 취소되었습니다.");
        return;
    }

    location.href = "/community/faqDelete?boardNo=" + boardNo;

});

const updateFaq = document.querySelector("#updateFaq");

updateFaq.addEventListener("submit", e => {

    const boardTitle = document.querySelector("#boardTitle");
    const boardContent = document.querySelector("#boardContent");

    if(boardTitle.value.trim().length === 0) {
        alert("질문 제목을 작성해주세요.");
        e.preventDefault();
        boardTitle.focus();
        return;
    }

    if(boardContent.value.trim().length === 0) {
        alert("질문 내용을 작성해주세요.");
        e.preventDefault();
        boardContent.focus();
        return;
    }

});