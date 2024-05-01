const loginForm = document.querySelector("#loginForm");

const loginPw = document.querySelector("#loginForm input[name='memberPw']");

// loginForm 이 화면에 존재할 때 (== 로그인 상태 아닐 때)
if(loginForm != null) {

  // 제출 이벤트 발생 시
  loginForm.addEventListener("submit", (e) => {

    // 이메일 미작성
    if(loginEmail.value.trim().length === 0) {
      alert("이메일을 작성해주세요!");
      e.preventDefault();   // 기본 이벤트(제출) 막기
      loginEmail.focus();   // 초점 이동
      return;
    }

    // 비밀번호 미작성
    if(loginPw.value.trim().length === 0) {
      alert("비밀번호를 작성해주세요!");
      e.preventDefault();   // 기본 이벤트(제출) 막기
      loginPw.focus();   // 초점 이동
      return;
    }

  });

}