/* 1:1 수정 화면 */
// 1:1 수정할 때
const mtmTitle = document.querySelector('#mtmTitle');
const mtmContent = document.querySelector('#mtmContent');

document.querySelector("#mtmSave").addEventListener("click", ()=>{
    
    const mtmTitle = document.querySelector("[name='mtmTitle']");
    const mtmContent = document.querySelector("[name='mtmContent']");

    if(mtmTitle.value.trim().length == 0){
        alert("제목을 작성해주세요.");
        mtmTitle.focus();
        e.preventDefault();
        return;
    }

    if(mtmContent.value.trim().length == 0){
        alert("문의 내용을 작성해주세요.");
        mtmTitle.focus();
        e.mtmContent();
        return;
    }
    
    const param = {
        "mtmTitle" : mtmTitle.value,
        "mtmContent" : mtmContent.value,
        "mtmNo" : mtmNo
    };

    fetch("/mypage/mtmUpdate" , {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(param)
    })
    .then(resp => resp.text())
    .then(result => {

        if(result > 0) {
            alert("문의가 정상적으로 수정되었습니다.");

            const urlSearch = new URLSearchParams(location.search);
            const cp = urlSearch.get('cp');

            opener.location.reload();

            location.href = location.pathname.replace('mtmUpdate', 'mtmDetail') + "?cp=" + cp;

        } else {
            alert("문의 수정 실패!");
        }
    })
});