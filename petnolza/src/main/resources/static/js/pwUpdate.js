// 비밀번호 변경하기
const pwUpdate = document.querySelector("#pwUpdate");

pwUpdate.addEventListener("submit", e => {

    const currPw = document.querySelector("#currPw");
    const newPw = document.querySelector("#newPw");
    const newPwConfirm = document.querySelector("#newPwConfirm");

    let str;

    if(currPw.value.trim().length === 0) str = "현재 비밀번호를 입력해주세요.";
    else if(newPw.value.trim().length === 0) str = "새 비밀번호를 입력해주세요.";
    else if(newPwConfirm.value.trim().length === 0) str = "새 비밀번호 확인을 입력해주세요.";

    

    if(str != undefined) {
        alert(str);
        e.preventDefault();
        return;
    }

    const regExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/

    if(!regExp.test(newPw.value)) {

        alert("비밀번호 형식이 올바르지 않습니다.(영어, 숫자, 특수문자를 포함한 8~20 글자)");
        e.preventDefault();
        return;

    }

    if(newPw.value != newPwConfirm.value) {

        alert("변경된 비밀번호가 일치하지 않습니다.");
        e.preventDefault();
        return;

    }

});