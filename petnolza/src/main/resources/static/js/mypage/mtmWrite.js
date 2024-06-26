/* 1:1 등록 화면 */
// 1:1 등록할 때
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
        "memberNo" : memberNo
    };

    fetch("/mypage/mtmInsert" , {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(param)
    })
    .then(resp => resp.text())
    .then(result => {

        if(result > 0) {
            console.log(result);
            alert("문의가 정상적으로 접수되었습니다.");

            opener.location.reload();

            location.href = location.pathname.replace('mtmWrite', 'mtmDetail') + '/' + result + "?cp=" + 1;

        } else {
            alert("문의 접수 실패!");
        }
    })
});
