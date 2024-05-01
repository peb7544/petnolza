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

    window.open(path,'','width='+popupW+',height='+popupH+',left='+left+',top='+top+',scrollbars=yes,resizable=no,toolbar=no,titlebar=no,menubar=no,location=no,fullscreen=no');
});