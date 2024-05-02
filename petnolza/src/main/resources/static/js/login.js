const loginForm = document.querySelector("#loginForm");

const loginEmail = document.querySelector("#loginForm input[name='memberEmail']");

const loginPassword = document.querySelector("#loginForm input[name='memberPassword']");

const saveIdCheck = document.querySelector("input[name='saveId']");


// 제출 이벤트 발생 시

loginForm.addEventListener("submit", (e) => {

  console.log("테스트");

  // 이메일 미작성
  if(loginEmail.value.trim().length === 0) {
    alert("이메일을 작성해주세요!");
    e.preventDefault();   
    loginEmail.focus();   
    return;
  }

  // 비밀번호 미작성
  if(loginPassword.value.trim().length === 0) {
    alert("비밀번호를 작성해주세요!");
    e.preventDefault();   
    loginPassword.focus();   
    return;
  }

});

