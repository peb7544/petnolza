const memberList = document.querySelector(".memberList").rows.length;

console.log(memberList);

for(let i = 0; i < memberList-1; i++) {

    console.log("테스트");

    document.querySelector('#row'+i).addEventListener("click", () => {

        const rowId = document.querySelector('#row'+i);
        const rowChild = rowId.children;

        location.href = location.pathname.replace('memberMana', 'memberInfo')
                        + '/' + rowChild[0].innerText;

    })

}

