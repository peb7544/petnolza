// 수정화면이동
document.querySelector('#mtmDetial').addEventListener('click', ()=>{

    const urlSearch = new URLSearchParams(location.search);
    const cp = urlSearch.get('cp');
        
    location.href = location.pathname.replace('mtmDetail', 'mtmUpdate') + "?cp=" + cp;

});


document.querySelector("#mtmDel").addEventListener("click", ()=>{
    
    if(!confirm("정말 삭제하시겠습니까?")) return;

    fetch("/mypage/mtmDelete" , {
        method : "DELETE",
        headers : {"Content-Type" : "application/json"},
        body : mtmNo
    })
    .then(resp => resp.text())
    .then(result => {

        if(result > 0) {
            console.log(result);
            alert("문의가 정상적으로 삭제되었습니다.");

            opener.location.reload(); // 새로고침
            window.close(); // 팝업창을 닫기

        } else {
            alert("문의 삭제 실패!");
        }
    })
});