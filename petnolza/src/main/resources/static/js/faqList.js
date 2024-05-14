const faqList = document.querySelector(".faqList").rows.length;
const faqRegist = document.querySelector("#faqRegist");


for(let i = 0; i < faqList-1; i++) {


    document.querySelector('#row'+i).addEventListener("click", () => {

        const rowId = document.querySelector('#row'+i);
        const rowChild = rowId.children;

        location.href = location.pathname.replace('faqList', 'faqDetail')
                        + '/' + rowChild[0].innerText;

    })

}

faqRegist.addEventListener("click", () => {

    location.href = "/community/faqRegist";

})