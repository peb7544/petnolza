const withdrawal = document.querySelector("#withdrawal");
const rejoin = document.querySelector("#rejoin");

withdrawal.addEventListener("click", e => {

    console.log(member.memberNickname);

    if( !confirm(`${member.memberNickname} 님을 탈퇴 시키겠습니까?`) ) {

        alert(`${member.memberNickname} 님의 탈퇴 진행이 취소되었습니다.`);
        return;

    }
});