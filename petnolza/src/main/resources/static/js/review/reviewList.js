console.group("ffffff");

// 팝업 경로
let path;

// 팝업 사이즈
const popupW = 750;
const popupH = 650;

// 팝업 위치
let tempLeft = Math.ceil((window.screen.width - popupW)/2);
let tempTop = Math.ceil((window.screen.height - popupH)/2);

// 상세화면이동
const listCnt = document.querySelector(".board-area").rows.length;

for(let i=0; i<listCnt-1; i++) {
    document.querySelector('#row'+i).addEventListener('click', ()=>{
        const rowId = document.querySelector('#row'+i);
        const rowChild = rowId.children;

        console.log(location);
        
        location.href = '../reviewDetail/' + rowChild[0].innerText + "?cp=" + cp;
        
        //window.open(path,'','width='+popupW+',height='+popupH+',left='+tempLeft+',top='+tempTop+',popup=true');

        // 팝업창 닫을 때 딤프레이어 block
        //document.getElementById("popOpen").style.display = "block";

    });
}

