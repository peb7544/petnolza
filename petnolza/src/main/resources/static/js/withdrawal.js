const withdrawal = document.querySelector("#withdrawal");

withdrawal.addEventListener("submit", e => {

    const memberPassword = document.querySelector("#memberPassword");

    if(memberPassword.value.trim().length === 0) {
        alert("비밀번호를 입력해주세요.");
        e.preventDefault();
        return;
    }

    

});