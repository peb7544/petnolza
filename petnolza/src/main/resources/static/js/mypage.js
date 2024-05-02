/* **************************** 1:1문의 ****************************  */

// 등록버튼
document.querySelector("#mtminsert").addEventListener("click", () => {

    // 팝업 사이즈
    const popupW = 750;
    const popupH = 650;

    // 팝업 위치
    let left = Math.ceil((window.screen.width - popupW)/2);
    let top = Math.ceil((window.screen.height - popupH)/2);

    // 경로
    const path = location.pathname.replace('mtmList', 'mtmWrite');

    window.open(path,'','width='+popupW+',height='+popupH+',left='+left+',top='+top+',popup=true');

    // 팝업창 닫을 때 딤프레이어 block
    document.getElementById("popOpen").style.display = "block";
});

// 상세화면이동
const mtmListCnt = document.querySelector(".mtmList").rows.length;

for(let i=0; i<mtmListCnt-1; i++) {
    document.querySelector('#row'+i).addEventListener('click', ()=>{
        const rowId = document.querySelector('#row'+i);
        const rowChild = rowId.children;
        
        location.href= '/mypage/mtmDetail?mtmNo=' + rowChild[0].innerText;

    });
}