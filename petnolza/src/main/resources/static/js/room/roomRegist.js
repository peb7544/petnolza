/* 선택된 이미지 미리보기 */
let previewList; // img 태그 5개
let inputImageList; // input 태그 5개
let deleteImageList; // x버튼 5개

// 이미지 선택 이후 취소를 누를 경우를 대비한 백업 이미지
// (백업 원리 -> 복제품으로 기존 요소를 대체함)
let backupInputList;
let backupInputList1 = [];

const createImageElement = (divCnt) => {
    // Create boardImg div element
    const boardImgDiv = document.createElement('div');
    boardImgDiv.classList.add('boardImg');
    boardImgDiv.id = `board${divCnt}`;

    // Create imgLabel label element
    const imgLabel = document.createElement('label');
    imgLabel.classList.add('imgLabel');
    imgLabel.id = `label${divCnt}`;
    imgLabel.htmlFor = `img${divCnt}`;

    // Create img preview element
    const imgPreview = document.createElement('img');
    imgPreview.classList.add('preview');
    imgLabel.appendChild(imgPreview); // Append img preview to imgLabel

    // Create inputImage input element
    const inputImage = document.createElement('input');
    inputImage.type = 'file';
    inputImage.name = 'images';
    inputImage.classList.add('inputImage');
    inputImage.id = `img${divCnt}`;
    inputImage.accept = 'image/*';
    imgLabel.appendChild(inputImage); // Append inputImage to imgLabel

    // Create radio label element
    const radioLabel = document.createElement('label');
    radioLabel.id = `radio${divCnt}`;
    radioLabel.htmlFor = `thumnail${divCnt}`;

    // Create radio input element
    const radioInput = document.createElement('input');
    radioInput.type = 'radio';
    radioInput.name = 'thumnailYn';
    radioInput.id = `thumnail${divCnt}`;
    radioInput.value = divCnt;
    radioLabel.appendChild(radioInput); // Append radioInput to radioLabel
    radioLabel.appendChild(document.createTextNode('대표이미지')); // Append text to radioLabel

    // Create delete image span element
    const deleteImageSpan = document.createElement('span');
    deleteImageSpan.classList.add('delete-image');
    deleteImageSpan.id = `del${divCnt}`;
    deleteImageSpan.innerHTML = '&times;';

    // Append all elements to boardImgDiv
    boardImgDiv.appendChild(imgLabel);
    boardImgDiv.appendChild(radioLabel);
    boardImgDiv.appendChild(deleteImageSpan);

    // Append boardImgDiv to .img-box element
    document.querySelector('.img-box').appendChild(boardImgDiv);
};


const handler = {
    init() {
        document.querySelector('.roomAppend').addEventListener('click', () => {

            let divCnt = document.querySelector('.img-box').childElementCount;
            
            inputImageList = document.getElementsByClassName("inputImage");

            //console.log("inputImageList : ",inputImageList.length);
            

            //console.log(divCnt);  

            if(Number(divCnt) < 10) {

                createImageElement(divCnt);

                /*backupInputList1 = new Array(divCnt);

                for(let i=0; i<divCnt; i++) {

                    backupInputList1[i] = inputImageList[i].cloneNode(true); // 노드 복사
                }
                
                
                document.querySelector('.img-box').innerHTML += `
                    <div class="boardImg" id="board` + divCnt + `">
                        <label class="imgLabel" id="label` + divCnt + `" for="img` + divCnt + `">
                            <img class="preview" src="">
                        </label>
                        <input type="file" name="images" class="inputImage" id="img` + divCnt + `" accept="image/*">
                        <label id="radio` + divCnt + `" for="thumnail` + divCnt + `" >
                            <input type="radio" name="thumnailYn" id="thumnail` + divCnt + `" value="` + divCnt +`">
                            대표이미지
                        </label>
                        <span class="delete-image" id="del` + divCnt + `">&times;</span>
                    </div>
                    `;

                
                for(let i=0; i<divCnt; i++) {
                    inputImageList[i].replaceWith( backupInputList1[i]); // 값 덮어씌우기
                }*/
               

        

                
            } else {
                alert("객실 이미지는 10개 미만으로 등록이 가능합니다");
            }
        });
    },

    changeImg : () => {
        document.addEventListener('change', (e) => {

            /* 선택된 이미지 미리보기 */
            previewList = document.querySelectorAll(".preview"); // img 태그 5개
            inputImageList = document.querySelectorAll(".inputImage"); // input 태그 5개
            deleteImageList = document.querySelectorAll(".delete-image"); // x버튼 5개

            // 이미지 선택 이후 취소를 누를 경우를 대비한 백업 이미지
            // (백업 원리 -> 복제품으로 기존 요소를 대체함)
            backupInputList = new Array(inputImageList.length);

            console.log("inputImageList.length : " + inputImageList.length);

            console.log(e.target.className);

            if(e.target.className != 'inputImage') return;

            console.log("이미지 맞다");

            console.log(e.target.id);
            /**** input태그에 이미지가 선택된 경우(값이 변경된 경우) ****/
            let inputImage = e.target.id;
            let order = inputImage.replace('img', '');

            console.log("e.target : " + e.target.id + " / id : " + order);

            changeImageFn(e.target, order, previewList, backupInputList);

        });
    },

    delImg : () => {
        document.addEventListener('click', (e) => {

            /* 선택된 이미지 미리보기 */
            //previewList = document.querySelectorAll(".preview"); // img 태그 5개
            //inputImageList = document.querySelectorAll(".inputImage"); // input 태그 5개
            //deleteImageList = document.querySelectorAll(".delete-image"); // x버튼 5개

            //let boardImageList = document.querySelectorAll(".boardImg"); // x버튼 5개

            

            //backupInputList = new Array(divCnt);

            if(e.target.className != 'delete-image') return;
            
            
               // **** x 버튼 클릭 시 ****
               // img, input, backup의 인덱스가 모두 일치한다는 특징을 이용

                let delBtn = e.target.id;
                let order = delBtn.replace('del', '');

                //

                let divCnt = document.querySelector('.img-box').childElementCount;

                for(let i=0; i<divCnt; i++) {

                    if(i == order) {
                        document.querySelector('#board' + i).setAttribute("id", "boardDel");
                    } if(i > order) { 
                        // 이미지
                        document.querySelector('#board' + i).setAttribute("id", "board" + (i-1));
                        document.querySelector('#label' + i).setAttribute("for", "img" + (i-1));
                        document.querySelector('#label' + i).setAttribute("id", "label" + (i-1));

                        // 이미지 삭제
                        document.querySelector('#img' + i).setAttribute("id", "img" + (i-1));
                        document.querySelector('#del' + i).setAttribute("id", "del" + (i-1));

                        // 대표이미지
                        document.querySelector('#thumnail' + i).setAttribute("id", "thumnail" + (i-1));
                        document.querySelector('#radio' + i).setAttribute("for", "thumnail" + (i-1));
                        document.querySelector('#radio' + i).setAttribute("id", "label" + (i-1));
                    }
                }

                document.getElementById("boardDel").remove(); // div 삭제

                // div 삭제
                
        });
    }
}

/* ***** input 태그 값 변경 시(파일 선택 시) 실행할 함수 ***** */
/**
 * @param inputImage : 파일이 선택된 input 태그
 * @param order : 이미지 순서
 */
const changeImageFn = (inputImage, order, previewList, backupInputList) => {

    // byte단위로 10MB 지정
    const maxSzie = 1024 * 1024 * 10;
  
    // 업로드된 파일 정보가 담긴 객체를 얻어와 변수에 저장
    const file = inputImage.files[0];
  
  
    // ------------- 파일 선택 -> 취소 해서 파일이 없는 경우 ----------------
    if(file == undefined){
      console.log("파일 선택 취소됨");
  
      // 같은 순서(order)번째 backupInputList 요소를 얻어와 대체하기
  
      /* 한 번 화면에 추가된 요소는 재사용(다른 곳에 또 추가) 불가능 */
  
      // 백업본을 한 번 더 복제
      const temp = backupInputList[order].cloneNode(true);
  
      inputImage.after(temp); // 백업본을 다음 요소로 추가
      inputImage.remove();    // 원본을 삭제
      inputImage = temp;      // 원본 변수에 백업본을 참조할 수 있게 대입
  
      // 백업본에 없는 이벤트 리스너를 다시 추가
      inputImage.addEventListener("change", e => {
        changeImageFn(e.target, order);
      })
  
      return;
    }
  
  
    // ---------- 선택된 파일의 크기가 최대 크기(maxSize) 초과 ---------
  
    if(file.size > maxSzie){
      alert("10MB 이하의 이미지를 선택해주세요");
  
      // 해당 순서의 backup 요소가 없거나, 
      // 요소는 있는데 값이 없는 경우 == 아무 파일도 선택된적 없을 때
      if(backupInputList[order] == undefined
          || backupInputList[order].value == ''){
  
        inputImage.value = ""; // 잘못 업로드된 파일 값 삭제
        return;
      }
  
      // 이전에 정상 선택 -> 다음 선택에서 이미지 크기 초과한 경우
      // 백업본을 한 번 더 복제
      const temp = backupInputList[order].cloneNode(true);
  
      inputImage.after(temp); // 백업본을 다음 요소로 추가
      inputImage.remove();    // 원본을 삭제
      inputImage = temp;      // 원본 변수에 백업본을 참조할 수 있게 대입
   
      // 백업본에 없는 이벤트 리스너를 다시 추가
      inputImage.addEventListener("change", e => {
       changeImageFn(e.target, order);
      })
  
      return;
    }
  
  
    // ------------ 선택된 이미지 미리보기 --------------
  
    const reader = new FileReader(); // JS에서 파일을 읽고 저장하는 객체
  
    // 선택된 파일을 JS로 읽어오기 -> reader.result 변수에 저장됨
    reader.readAsDataURL(file);
  
    reader.addEventListener("load", e => {
      const url = e.target.result;
  
      // img 태그(.preview)에 src 속성으로 url 값을 대입
      previewList[order].src = url;
  
      // 같은 순서 backupInputList에 input태그를 복제해서 대입
      backupInputList[order] = inputImage.cloneNode(true);
    });
  
  }


handler.init();
handler.changeImg();
handler.delImg();

/* 목록 */
document.querySelector('#cancelBtn').addEventListener('click', () => {
    location.href = '/room/roomList';
});


// 작성 폼 유효성 검사
document.querySelector("#roomRegist").addEventListener("submit", e => {

    const roomName = document.querySelector("[name='roomName']");
    const roomInfo = document.querySelector("[name='roomInfo']");
    const imgCnt = document.querySelector('.img-box');
    const roomPrice = document.querySelector("[name='roomPrice']");

    if(roomName.value.trim().length == 0){
        alert("객실 이름을 작성해주세요.");
        roomName.focus();
        e.preventDefault();
        return;
    }

    if(roomInfo.value.trim().length == 0){
        alert("객실 상세 내용을 작성해주세요.");
        roomInfo.focus();
        e.preventDefault();
        return;
    }

    if(imgCnt.childElementCount == 0) {
        alert("객실 이미지를 등록해주세요.");
        imgCnt.focus();
        e.preventDefault();
        return;
    } 

    if(imgCnt.childElementCount != 0) {
        for(let i = 0; i < imgCnt.childElementCount; i++) {

            if(!document.querySelector('#img' + i).value) {
                alert("객실 이미지를 등록해주세요.");
                imgCnt.focus();
                e.preventDefault();
                return;
            }

            
        }
    }

    if(document.querySelector("[name='thumnailYn']:checked").value
 == "") {
        alert("대표로 보여질 이미지를 선택해주세요.");
        imgCnt.focus();
        e.preventDefault();
        return;
    }

    if(roomPrice.value.trim().length == 0){
        alert("객실 가격을 작성해주세요.");
        roomPrice.focus();
        e.preventDefault();
        return;
    }

});