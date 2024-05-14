const update = document.querySelector("#update");

update.addEventListener("click", () => {

    location.href = "/review/reviewUpdate?reviewNo=" + reviewNo;

});

const backToList = document.querySelector("#backToList");

backToList.addEventListener("click", () => {

    console.log("aaa");

    location.href = "/review/reviewList/" + roomId;

});

const deleteReview = document.querySelector("#deleteReview");

deleteReview.addEventListener("click", () => {

    console.log(reviewNo + "+" + roomId);

    location.href = "/review/reviewdeleteReview?reviewNo=" + reviewNo + "&roomId=" + roomId;

});

