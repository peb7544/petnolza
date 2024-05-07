function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
  
            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
  
            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }
  
            
  
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("postcode").value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
  }
  

// =============================================================================================



const checkObj = {
    "memberEmail"      : false,
    "memberPassword"   : false,
    "memberPassword2"  : false,
    "memberNickname"   : false,
    "memberTel"        : false
  }


// 이용 약관 체크 박스
const agreeCheck = document.querySelector("#agreeCheck");

// 회원가입 form 태그
const join = document.querySelector("#joinForm");

// 이메일 요소 
const memberEmail = document.querySelector("#memberEmail");
const emailText = document.querySelector("#emailText")

// 비밀번호 요소
const memberPassword = document.querySelector("#memberPassword");
const memberPassword2 = document.querySelector("#memberPassword2");
const passwordText = document.querySelector("#passwordText");


const memberName = document.querySelector("#memberName");

// 닉네임 요소
const memberNickname = document.querySelector("#memberNickname");
const nicknameText = document.querySelector("#nicknameText");

// 전화번호 요소
const memberTel = document.querySelector("#memberTel");
const telText = document.querySelector("#telText");

const memberAddress = document.querySelector("#memberAddress");


// 이메일 유효성 검사
memberEmail.addEventListener("input", e => {

    const regExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    const inputEmail = e.target.value;

    if(inputEmail.trim().length === 0) {
        emailText.innerText = "이메일을 입력해주세요";
        emailText.classList.remove('error', 'confirm');
        checkObj.memberEmail = false;
        memberEmail.value = "";
        return;
    }

    if( !regExp.test(inputEmail) ) {
        emailText.innerText = "이메일 형식이 올바르지 않습니다.";
        emailText.classList.add('error');
        emailText.classList.remove('confirm');
        checkObj.memberEmail = false;
        return;
    } 

    fetch("/member/checkEmail?memberEmail=" + inputEmail)
    .then( resp => resp.text() )
    .then( count => {

        if(count == 1) {
            emailText.innerText = "이미 사용중인 이메일 입니다.";
            emailText.classList.add('error');
            emailText.classList.remove('confirm');
            checkObj.memberEmail = false;
            return;
        }

        emailText.innerText = "사용 가능한 이메일입니다.";
        emailText.classList.add('confirm');
        emailText.classList.remove('error');
        checkObj.memberEmail = true;

    })
    .catch(error => {
        console.log(error);
    })

});


// 비밀번호 / 비밀번호확인 검사
const checkPw = () => {

    if(memberPassword.value === memberPassword2.value) {
        passwordText.innerText = "비밀번호가 일치합니다.";
        passwordText.classList.add('confirm');
        passwordText.classList.remove('error');
        checkObj.memberPassword2 = true;
        return;
    }

    passwordText.innerText = "비밀번호가 일치하지 않습니다.";
    passwordText.classList.add('error');
    passwordText.classList.remove('confirm');
    checkObj.memberPassword2 = false;

};


memberPassword.addEventListener("input", e => {

    const inputPassword = e.target.value;

    if(inputPassword.trim().length === 0) {
        passwordText.innerText = "영어, 숫자, 특수문자를 포함한 8~20글자 사이로 입력해주세요.";
        passwordText.classList.remove('error', 'confirm');
        checkObj.memberPassword = false;
        memberPassword.value = "";
        return;
    }

    const regExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/

    if( !regExp.test(inputPassword) ) {
        passwordText.innerText = "영어, 숫자, 특수문자를 포함한 8~20글자 사이로 입력해주세요.";
        passwordText.classList.add('error');
        passwordText.classList.remove('confirm');
        checkObj.memberPassword = false;
        return;
    }

    passwordText.innerText = "사용가능한 비밀번호 입니다.";
    passwordText.classList.add('confirm');
    passwordText.classList.remove('error');
    checkObj.memberPassword = true;

    if(memberPassword2.value.length > 0) {
        checkPw();
    }

});

// 비밀번호 확인
memberPassword2.addEventListener("input", () => {

    if(checkObj.memberPassword) {
        checkPw();
        return;
    }

    checkObj.memberPassword2 = false;

})


// 닉네임 유효성 검사
memberNickname.addEventListener("input", e => {

    const inputNickname = e.target.value;

    if(inputNickname.trim().length === 0) {
        nicknameText.innerText = "한글, 영어, 숫자를 이용한 2~10글자의 닉네임을 입력해주세요."
        nicknameText.classList.remove('error', 'confirm');
        checkObj.memberNickname = false;
        memberNickname.value = "";
        return;
    }

    const regExp = /^[가-힣\w\d]{2,10}$/;

    if(!regExp.test(inputNickname)) {

        nicknameText.innerText = "닉네임 형식이 올바르지 않습니다.";
        nicknameText.classList.add('error');
        nicknameText.classList.remove('confirm');
        checkObj.memberNickname = false;
        return;
    }

    fetch("/member/checkNickname?memberNickname=" + inputNickname)
    .then( resp => resp.text() )
    .then( result => {
        
        if (result == 1) {
            nicknameText.innerText = "이미 사용중인 닉네임 입니다.";
            nicknameText.classList.add('error');
            nicknameText.classList.remove('confirm');
            checkObj.memberNickname = false;
            return;
        }
        
        nicknameText.innerText = "사용 가능한 닉네임 입니다.";
        nicknameText.classList.add('confirm');
        nicknameText.classList.remove('error');
        checkObj.memberNickname = true;

    })
    .catch( err => console.log(err) );

})


// 전화번호 유효성
memberTel.addEventListener("input", e => {

    const inputTel = e.target.value;

    if(inputTel.trim().length === 0) {
        telText.innerText = "전화번호를 입력해주세요('-' 제외)";
        telText.classList.remove('error', 'confirm');
        checkObj.memberTel = false;
        memberTel.value = "";
        return;
    }

    const regExp = /^01[0-9]{1}[0-9]{3,4}[0-9]{4}$/;

    if(!regExp.test(inputTel)) {

        telText.innerText = "전화번호 형식이 올바르지 않습니다.";
        telText.classList.add('error');
        telText.classList.remove('confirm');
        checkObj.memberTel = false;
        return;

    }

    telText.innerText = "전화번호 입력이 완료되었습니다.";
    telText.classList.add('confirm');
    telText.classList.remove('error');
    checkObj.memberTel = true;

})


join.addEventListener("submit", e => {

    if(!agreeCheck.checked) {
        console.log("확인");
        alert("이용약관에 동의해주세요.");
        e.preventDefault();
        location.href = "#terms"
        // 이용약관 체크 박스로 이동하기 추가
        return;
    }

    for(let key in checkObj) {  // checkObj 요소의 key 값을 순서대로 꺼내옴

        if( !checkObj[key] ) {    // false 인 경우 (유효하지 않음)
          
          let str;    // 출력할 메시지를 저장할 변수
          
          switch(key) {
            case "memberEmail" : str = "이메일이 유효하지 않습니다."; break;
            case "memberPassword" : str = "비밀번호가 유효하지 않습니다."; break;
            case "memberPassword2" : str = "비밀번호가 일치하지 않습니다."; break;
            case "memberNickname" : str = "닉네임이 유효하지 않습니다."; break;
            case "memberTel" : str = "전화번호가 유효하지 않습니다."; break;
          }
    
          alert(str);
    
          document.getElementById(key).focus(); // 초점 이동
    
          e.preventDefault();   // form 태그 기본 이벤트(제출) 막기
          return;
    
        }
    
      }

})