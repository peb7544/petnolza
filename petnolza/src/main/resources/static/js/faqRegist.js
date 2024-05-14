const boardTitle = document.querySelector("#boardTitle");
const boardContent = document.querySelector("#boardContent");

const faqRegist = document.querySelector("#faqRegist");

faqRegist.addEventListener("submit", e => {

    if(boardTitle.value.trim().length === 0) {
        alert("질문 제목을 입력해주세요.");
        boardTitle.focus();
        e.preventDefault();
        return;
    }

    if(boardContent.value.trim().length === 0) {
        alert("질문 내용을 입력해주세요.");
        boardContent.focus();
        e.preventDefault();
        return;
    }

});