function reviewBtn(roomId,roomName) {

   // 팝업 경로
   let path;

   // 팝업 사이즈
   const popupW = 750;
   const popupH = 650;

   // 팝업 위치
   let tempLeft = Math.ceil((window.screen.width - popupW)/2);
   let tempTop = Math.ceil((window.screen.height - popupH)/2);

   // 경로
   path = location.pathname.replace('reserve/reserveList', 'review/reviewList') +  "/"  +  roomId +"?cp=" + 1;

   window.open(path,'','width='+popupW+',height='+popupH+',left='+tempLeft+',top='+tempTop+',popup=true');

   // 팝업창 닫을 때 딤프레이어 block
   document.getElementById("popOpen").style.display = "block";

 }