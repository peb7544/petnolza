const update = document.querySelector("#update");


const backToList = document.querySelector("#backToList");

backToList.addEventListener("click", () => {
    
    console.log("aaa");
    
    location.href = "/review/reviewList/" + roomId;
    
});

if(update != null) {

    update.addEventListener("click", () => {
    
        location.href = "/review/reviewUpdate?reviewNo=" + reviewNo;
    
    });

}


const deleteReview = document.querySelector("#deleteReview");

if(deleteReview != null) {

    deleteReview.addEventListener("click", () => {
    
        if(!confirm("해당 후기를 삭제하시겠습니까?")) {
            alert("해당 후기 삭제를 취소하였습니다.");
            return;
        }
    
        location.href = "/review/reviewdeleteReview?reviewNo=" + reviewNo + "&roomId=" + roomId;
    
    });

}


