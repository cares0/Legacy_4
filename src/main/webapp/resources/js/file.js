const fileResult = document.getElementById('fileResult');
const fileAdd = document.getElementById('fileAdd');
const del = document.getElementsByClassName("del");


let check = 0;
let num = 0; // id 용

fileAdd.addEventListener("click", function() {
    
    if(check > 4) {
        alert('첨부파일은 최대 5개까지 가능합니다.')
        return;
    }
    check++;
    
    let div = document.createElement('div'); // <div>
    div.setAttribute("id", "del"+num);
    
    let file = document.createElement("input");
    file.setAttribute("type", "file");
    file.setAttribute("name", "files");
    
    let button = document.createElement('button');
    button.setAttribute("type", "button");
    button.className = "del";
    button.setAttribute("data-num", "del"+num)
    button.innerHTML = "DEL";
    
    div.append(file);
    div.append(button);    
    
    fileResult.append(div);
    console.log(check);
   
    num++;
});

fileResult.addEventListener("click", function(event){
    let cn = event.target;
    // console.log(event.target.classList.contains('del'));

    if(cn.classList.contains('del')) { // 클릭한 요소의 클래스에 'del'이 포함되어 있느냐?
        let delNum = cn.getAttribute("data-num"); // 속성명이 data-num인 값을 delNum에 저장
        document.getElementById(delNum).remove(); // Id가 delNum인 요소를 지움 (파일과 버튼 전체를 감싸는 div태그임)
        check--;
    }
})

const fileDeleteBtn = document.querySelectorAll(".fileDeleteBtn");
const files = document.querySelector("#files");

files.addEventListener("click", function(event){
    if(event.target.classList.contains('fileDeleteBtn')) {
        let check = confirm("삭제시 복구 불가능 합니다. 삭제하시겠습니까?");
        
        if(!check){
            return;
        }

        let fileNum = event.target.getAttribute("data-fileNum");

        let xhttp = new XMLHttpRequest();

        xhttp.open("POST", "./fileDelete");

        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        xhttp.send("fileNum="+fileNum);

        xhttp.onreadystatechange = function() {
            if(this.readyState == 4 & this.status == 200) {
                let result = this.responseText.trim();
                if(result=='1') {
                    console.log('파일 삭제');

                    // 클릭한 삭제버튼의 부모요소를 삭제해버림
                    event.target.parentNode.remove();
                } else {
                    console.log('삭제실패');
                }
            
            }
        }
    }
})