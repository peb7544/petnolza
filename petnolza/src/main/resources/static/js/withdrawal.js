const withdrawal = document.querySelector("#withdrawal");

withdrawal.addEventListener("submit", e => {

    const memberPassword = document.querySelector("#memberPassword");
    const agreeCheck = document.querySelector("#agreeCheck");

    if( !agreeCheck.checked ) {

        alert("위 약관에 동의해주세요.");
        e.preventDefault();
        return;

    }

    if(memberPassword.value.trim().length === 0) {
        alert("비밀번호를 입력해주세요.");
        e.preventDefault();
        return;
    }



    if( !confirm(`${memberName} 님 회원탈퇴를 진행하시겠습니까?`) ) {

        alert("회원탈퇴가 취소되었습니다.");
        e.preventDefault();
        memberPassword.value = "";
        agreeCheck.checked = false;
        return;

    }
    

});