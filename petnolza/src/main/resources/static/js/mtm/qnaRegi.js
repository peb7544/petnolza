/* 답변 처리 */
const mtmAnswer = document.querySelector('#mtmAnswer');

document.querySelector("#mtmSave").addEventListener("click", ()=>{
    
    const param = {
        "mtmAnswer" : mtmAnswer.value,
        "mtmNo" : mtmNo
    };

    fetch("/mtm/mtmAnswer" , {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(param)
    })
    .then(resp => resp.text())
    .then(resulet => {

        if(result > 0) {
            alert("답변이 정상적으로 등록되었습니다.");

            const urlSearch = new URLSearchParams(location.search);
            const cp = urlSearch.get('cp');

            opener.location.reload();

            location.href = location.pathname.replace('mtmUpdate', 'mtmDetail') + "?cp=" + cp;

        } else {
            alert("답변 등록 실패!");
        }
    })
});