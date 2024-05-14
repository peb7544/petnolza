 /* 서비스 등록 */
 const serviceName = document.querySelector('#serviceName');
 const servicePrice = document.querySelector('#servicePrice');
 
 document.querySelector("#addBtn").addEventListener("click", ()=>{
     
     const param = {
         "serviceName" : serviceName.value,
         "servicePrice" : servicePrice.value
     };
 
     fetch("/service/insertService" , {
         method : "POST",
         headers : {"Content-Type" : "application/json"},
         body : JSON.stringify(param)
     })
     .then(resp => resp.text())
     .then(result => {
 
         if(result > 0) {
             console.log(result);
             alert("서비스가 정상적으로 등록되었습니다.");
 
             location.reload();

             // 팝업창 닫을 때 딤프레이어 block
             opener.document.getElementById("popOpen").style.display = "block";
 
         } else {
             alert("서비스 등록 실패!");
         }
     })
 });

 /* 서비스 삭제 */
function delBtn(serviceNo) {
    
    if(!confirm("정말 삭제하시겠습니까?")) return;

    fetch("/service/deleteService" , {
        method : "DELETE",
        headers : {"Content-Type" : "application/json"},
        body : serviceNo
    })
    .then(resp => resp.text())
    .then(result => {

        if(result > 0) {
            alert("서비스가 정상적으로 삭제되었습니다.");

            location.reload(); // 새로고침

            // 팝업창 닫을 때 딤프레이어 block
            opener.document.getElementById("popOpen").style.display = "block";

        } else {
            alert("서비스 삭제 실패!");
        }
    })
}