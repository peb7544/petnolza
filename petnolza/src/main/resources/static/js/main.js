/* 자주묻는질문 */

// 답변 보기 닫기
function listClickFn(element) {

    // 기존에 활성화된 버튼
    const before = document.getElementsByClassName("active")[0];

    // 자신 이외에 이미 활성화된 버튼이 있을 경우
    if(before && before != element) {

        // 기존에 펼쳐진 내용 접고
        before.nextElementSibling.style.display = 'none'; 

        // 버튼 비활성화
        before.classList.remove("active");
    }

    // 활성화 여부 toggle
    element.classList.toggle("active");

    const content = element.nextElementSibling;

    // 버튼 다음 요소가 펼쳐져 있으면
    if(content.style.display != 'none') {

        // 접기
        content.style.display = 'none';
    } else {

        // 접혀있는 경우 펼치기
        content.style.display = 'contents';
    }
}