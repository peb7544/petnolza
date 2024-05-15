console.log("ddddd");

/* 체크박스 */
const serviceCnt = document.querySelector("#serviceDiv").childElementCount;

let totalPrice = document.querySelector('#totalPrice') // 총가격

for(let i=0; i<serviceCnt; i++) {

    document.querySelector('#row'+i).addEventListener('click', () => {

        const servicePrice = document.querySelector('#row'+i).parentNode.children[1];

        totalV = Number(totalPrice.value);

        console.log(totalV);
        console.log(totalV);

        if(document.querySelector('#row'+i).checked) 
            totalV += Number(servicePrice.innerText);
        else  totalV -= Number(servicePrice.innerText);

        totalPrice.value = totalV;
    });
}

/* 목록 */
document.querySelector('#cancelBtn').addEventListener('click', () => {
    location.href = '../reserveList';
});

// 작성 폼 유효성 검사
document.querySelector("#reserveRegist").addEventListener("submit", e => {

    const reserveUser = document.querySelector("[name='reserveUser']");

    if(reserveUser.value.trim().length == 0){
        alert("예약자를 작성해주세요.");
        reserveUser.focus();
        e.preventDefault();
        return;
    }

});


function dateDif() {

    // 날짜 계산
    const start = document.querySelector("[name='reserveStart']").value;
    const startDate = new Date(start.substr(0,4) + "-" +  start.substr(4,2) + "-" +  start.substr(6,2));

    const end = document.querySelector("[name='reserveEnd']").value;
    const endDate = new Date(end.substr(0,4) + "-" +  end.substr(4,2) + "-" +  end.substr(6,2))

    let diff = Math.abs(endDate.getTime() - startDate.getTime());
    diff = Math.ceil(diff / (1000 * 60 * 60 * 24));

    // 예약 가격
    const price = document.querySelector("[name='roomPrice']").innerText;

    document.querySelector('#totalPrice').value = Number(price) * Number(diff);
}

dateDif(); // 날짜 계산