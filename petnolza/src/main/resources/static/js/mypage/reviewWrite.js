opener.document.getElementById("popOpen").style.display = "block";

// 팝업창 닫을 때 딤프레이어 none
window.addEventListener('beforeunload', function(event) {
    opener.document.getElementById("popOpen").style.display = "none";
});