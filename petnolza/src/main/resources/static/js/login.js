const loginForm = document.querySelector("#loginForm");

const loginEmail = document.querySelector("#memberEmail");

const loginPassword = document.querySelector("#loginForm input[name='memberPassword']");


const getCookie = (key) => {

  const cookies = document.cookie;  
  const cookieList = cookies.split("; ")    // ["K=V", "K=V", "K=V"]
                            .map( el => el.split("=") );   // ["K", "V"] ...

  
  const obj = {};   // 비어있는 객체 선언

  for(let i = 0; i < cookieList.length; i++) {
    const k = cookieList[i][0]; // key 값
    const v = cookieList[i][1]; // value 값
    obj[k] = v;
  }

  
  return obj[key];  
};


if(loginEmail != null) {  // 로그인창의 이메일 입력부분이 화면에 있을 때

  // 쿠키 중 key 값이 "saveId"인 요소의 value 값 얻어오기
  const saveId = getCookie("saveId");   // undefined or 이메일

  // saveId 값이 있을 경우
  if(saveId != undefined) {
    loginEmail.value = saveId;  // 쿠키에서 얻어온 값을 input 에 value 로 세팅

    // 아이디 저장 체크박스에 체크 해두기
    document.querySelector("input[name='saveId']").checked = true;
  }

};




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

