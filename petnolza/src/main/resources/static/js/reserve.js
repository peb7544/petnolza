/* 예약버튼 */
/*const reserveCnt = document.querySelector(".max-container").childElementCount;

for(let i=0; i<reserveCnt-1; i++) {
    document.querySelector('#row'+i).addEventListener('click', ()=>{
        const rowId = document.querySelector('#row'+i).parentNode.children[1];
        let inputStart = document.querySelector('#inputStart').value;
        let inputEnd = document.querySelector('#inputEnd').value;

        location.href = location.pathname.replace('reserveList', 'reserveRegist') + '/' 
                        + rowId.innerText + "?inputStart=" + inputStart + ",inputEnd="+inputEnd;
    });
}*/

/*const mtmListCnt = document.querySelector(".mtmList").rows.length;

for(let i=0; i<mtmListCnt-1; i++) {
    document.querySelector('#row'+i).addEventListener('click', ()=>{
        const rowId = document.querySelector('#row'+i);
        const rowChild = rowId.children;
        
        path = location.pathname.replace('mtmList', 'mtmDetail') + '/' + rowChild[0].innerText + "?cp=" + cp;

        window.open(path,'','width='+popupW+',height='+popupH+',left='+tempLeft+',top='+tempTop+',popup=true');

        // 팝업창 닫을 때 딤프레이어 block
        document.getElementById("popOpen").style.display = "block";

    });
}*/