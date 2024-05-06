// 팝업 경로
let path;

// 팝업 사이즈
const popupW = 750;
const popupH = 650;

// 팝업 위치
let tempLeft = Math.ceil((window.screen.width - popupW)/2);
let tempTop = Math.ceil((window.screen.height - popupH)/2);

// 상세화면이동
const mtmListCnt = document.querySelector(".qnaList").rows.length;

for(let i=0; i<mtmListCnt-1; i++) {
    document.querySelector('#row'+i).addEventListener('click', ()=>{
        const rowId = document.querySelector('#row'+i);
        const rowChild = rowId.children;
        
        path = location.pathname.replace('qna', 'qnaRegi') + '/' + rowChild[0].innerText;

        window.open(path,'','width='+popupW+',height='+popupH+',left='+tempLeft+',top='+tempTop+',popup=true');

        // 팝업창 닫을 때 딤프레이어 block
        document.getElementById("popOpen").style.display = "block";

    });
}