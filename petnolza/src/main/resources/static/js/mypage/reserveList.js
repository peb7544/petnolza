function btnClick(reserveNo, roomId, status) {

    if(status == "R") { // 후기 버튼

        // 팝업 경로
        let path;

        // 팝업 사이즈
        const popupW = 750;
        const popupH = 650;

        // 팝업 위치
        let tempLeft = Math.ceil((window.screen.width - popupW)/2);
        let tempTop = Math.ceil((window.screen.height - popupH)/2);

        // 경로
        path = location.pathname.replace('reserveList', 'reviewWrite') +  "/"  +  roomId +"?cp=" + cp;

        window.open(path,'','width='+popupW+',height='+popupH+',left='+tempLeft+',top='+tempTop+',popup=true');

        // 팝업창 닫을 때 딤프레이어 block
        //document.getElementById("popOpen").style.display = "block";

    } else { // 취소, 결제 버튼

        if(!confirm("객실 예약을 " + status + "하시겠습니까?")) return;

        const param = {
            status : status,
            reserveNo : reserveNo
        }
        
        fetch("/mypage/reserveUpdate", {
            method : "PUT",
            headers : {"Content-type" : "application/json"},
            body : JSON.stringify(param)
        })
        .then(resp => resp.text())
        .then(result => {
            if(result > 0) {
                alert("객실 예약이 " + status + "되었습니다");

                location.reload();
            }
        })

    }

 }