function execDaumPost() {
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


// ===============================================================================================


const updateForm = document.querySelector("#updateForm");
const memberName = document.querySelector("#memberName");
const memberNickname = document.querySelector("#memberNickname");
const memberTel = document.querySelector("#memberTel");
const memberAddr = document.querySelectorAll("[name= 'memberAddr']");

updateForm.addEventListener("submit", e => {


    // 이름
    if(memberName.value.trim().length === 0) {
        alert("이름을 입력해주세요.");
        e.preventDefault();
        return;
    }

    // 닉네임 유효성 검사
    if(memberNickname.value.trim().length === 0) {
        alert("닉네임을 입력해주세요.");
        e.preventDefault();
        return;
    }

    let regExp = /^[가-힣\w\d]{2,10}$/;
    if( !regExp.test(memberNickname.value) ) {
        alert("닉네임이 유효하지 않습니다.");
        e.preventDefault();
        return;
    }


    // 전화번호 유효성 검사
    if(memberTel.value.trim().length === 0) {
        alert("전화번호를 입력해주세요.");
        e.preventDefault();
        return;
    }

    regExp = /^01[0-9]{1}[0-9]{3,4}[0-9]{4}$/;
    if( !regExp.test(memberTel.value) ) {
        alert("전화번호가 유효하지 않습니다");
        e.preventDefault();
        return;
    }

    
    // 주소 유효성 검사
    const addr0 = memberAddr[0].value.trim().length == 0;    // true or false
    const addr1 = memberAddr[1].value.trim().length == 0;
    const addr2 = memberAddr[2].value.trim().length == 0;

    // 모두 true 인 경우 true 저장
    const result1 = addr0 && addr1 && addr2;    // 아무것도 입력 X

    // 모두 false 인 경우 true 저장
    const result2 = !(addr0 || addr1 || addr2); // 모두 다 입력

    // 모두 입력 또는 모두 미입력이 아니면
    if( !(result1 || result2) ) {

        alert("주소는 미작성하시거나 모든 주소를 상세히 입력해주세요.");
        e.preventDefault();

    }
    
});


// ====================================================================================

