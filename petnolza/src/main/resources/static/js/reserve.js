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