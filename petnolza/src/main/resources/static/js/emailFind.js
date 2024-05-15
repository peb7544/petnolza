const memberTel = document.querySelector("#memberTel");
const memberEmail = document.querySelector("#memberEmail");

const emailFindBtn = document.querySelector("#emailFindBtn");
const pwFindBtn = document.querySelector("#pwFindBtn");

emailFindBtn.addEventListener("click", () => {

    const inputTel = memberTel.value;
    
    if(inputTel.trim().length === 0) {
      
        alert("찾으시려는 이메일에 등록된 전화번호를 입력해주세요.");
        return;

    };

    fetch("/member/emailFind?inputTel=" + inputTel, {
        method : "POST"
    })
    .then(resp => resp.text())
    .then(result => {

        console.log(result);
        
        if(result == "") {
            alert("입력한 전화번호와 일치하는 아이디가 없습니다.");
            return;
        }

        alert("이메일 : " + result );

    })
    .catch(err => console.log(err));

});

pwFindBtn.addEventListener("click", () => {

    const inputEmail = memberEmail.value;

    if(inputEmail.trim().length === 0) {
        alert("이메일을 입력해주세요.");
        return;
    };

    fetch("/member/pwFind", {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : inputEmail
    })
    .then(resp => resp.text())
    .then(result => {

        if(result == 0) {
            alert("입력하신 이메일과 일치하는 이메일이 존재하지 않습니다.");
            return;
        }

        alert("임시 비밀번호를 전송하였습니다. 로그인 후 비밀번호를 변경해주세요.");

    })
    .catch(err => console.log(err));

});