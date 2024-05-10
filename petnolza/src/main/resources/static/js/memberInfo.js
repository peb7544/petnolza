const withdrawal = document.querySelector("#withdrawal");
const rejoin = document.querySelector("#rejoin");
const alterAdmin = document.querySelector("#alterAdmin");
const alterNormal = document.querySelector("#alterNormal");

if( withdrawal != null) {

    withdrawal.addEventListener("click", () => {
        
        
        if( !confirm(`${memberNickname} 님을 탈퇴 시키겠습니까?`) ) {
            
            alert(`${memberNickname} 님의 탈퇴 진행이 취소되었습니다.`);
            return;
            
        } else {
            
            location.href = "/member/withdrawal?memberNo=" + memberNo;
            
        }
    });


}

if( rejoin != null ) {

    rejoin.addEventListener("click", () => {
    
        if( !confirm(`${memberNickname} 님을 재가입 시키겠습니까?`) ) {
    
            alert(`${memberNickname} 님의 재가입이 취소되었습니다.`)
            return;
    
        } else {
    
            location.href = "/member/rejoin?memberNo=" + memberNo;
    
        }
    
    })

}

if( alterAdmin != null ) {


    alterAdmin.addEventListener("click", () => {
    
        if( !confirm(`${memberNickname} 님을 관리자로 변경하시겠습니까?`) ) {
    
            alert("관리자로 변경을 취소하였습니다");
            return;
    
        } else {
    
            location.href = "/member/alterAdmin?memberNo=" + memberNo;
    
        }
    
    });

};


if( alterNormal != null ) {

    alterNormal.addEventListener("click", () => {

        if(!confirm(`${memberNickname} 님을 일반회원으로 변경하시겠습니까?`)) {

            alert("일반회원으로 변경을 취소하였습니다.");
            return;

        } else {

            location.href = "/member/alterNormal?memberNo=" + memberNo; 

        }

    });

}

