/* 객실 삭제 */
function delBtn(roomId) {
    
    if(!confirm("정말 삭제하시겠습니까?")) return;

    fetch("/room/roomDelete" , {
        method : "DELETE",
        headers : {"Content-Type" : "application/json"},
        body : roomId
    })
    .then(resp => resp.text())
    .then(result => {

        if(result > 0) {
            alert("객실이 정상적으로 삭제되었습니다.");

            location.reload(); // 새로고침

        } else {
            alert("객실 삭제 실패!");
        }
    })
}

/* 서비스 등록 */
// 등록버튼
document.querySelector("#serviceRegist").addEventListener("click", () => {

    // 팝업 경로
    let path;

    // 팝업 사이즈
    const popupW = 750;
    const popupH = 650;

    // 팝업 위치
    const tempLeft = Math.ceil((window.screen.width - popupW)/2);
    const tempTop = Math.ceil((window.screen.height - popupH)/2);

    // 경로
    path = location.pathname.replace('room/roomList', 'service/serviceMana');

    window.open(path,'','width='+popupW+',height='+popupH+',left='+tempLeft+',top='+tempTop+',popup=true');

    // 팝업창 닫을 때 딤프레이어 block
    document.getElementById("popOpen").style.display = "block";
});