const reply = document.querySelector("#reply");
const num = document.querySelector("#num");
const writer = document.querySelector("#writer");
const contents = document.querySelector("#contents");
const replyResult = document.querySelector("#replyResult");

replyResult.addEventListener("click", function(event){
    if(event.target.classList.contains('update')) {
        // event.target.classList.replace('update', 'reply');
        // 상대경로로 선택하는법 : 너무 복잡, 부모를 찾거나 가까울 때나 사용
        // console.log(event.target.parentNode.previousSibling.previousSibling.previousSibling.previousSibling);

        let num = event.target.getAttribute('data-index');
        let replyNum = document.querySelector("#up"+num);

        let text = replyNum.innerText;
        replyNum.innerText = "";

        let tx = document.createElement('textarea');
        tx.setAttribute('id', 'update'+num);
        tx.classList.add('reply');
        tx.setAttribute("data-num", num);
        tx.value = text;
        replyNum.append(tx);
    }
})

replyResult.addEventListener("change", function(event){
    if(event.target.classList.contains('reply')) {
        let contents = event.target.value;
        let replyNum = event.target.getAttribute('data-num');

        let check = confirm("수정하시겠습니까?"); // 확인 : true, 취소 : false
        
        if(check) {
            let xhttp = new XMLHttpRequest();

            xhttp.open("POST", "../noticeReply/update");

            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

            xhttp.send("replyNum="+replyNum+"&contents="+contents);

            xhttp.onreadystatechange = function() {
                if(this.readyState == 4 & this.status == 200) {
                    let result = this.responseText.trim();
                    console.log(result);
                    if(result == '1') {
                        alert('댓글이 수정되었습니다.');
                        // 수정된 내용으로 td태그의 내용을 덮어쓰기
                        document.querySelector("#up"+replyNum).innerHTML=contents;
                    } else {
                        alert('댓글수정에 실패했습니다.');
                    }
                }
            }
        }
    }
})

replyResult.addEventListener("click", function(event){
    if(event.target.classList.contains('del')) {
        let replyNum = event.target.getAttribute('data-num');
        
        const xhttp = new XMLHttpRequest();

        xhttp.open("POST", "../noticeReply/delete");

        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        xhttp.send("replyNum="+replyNum);

        xhttp.onreadystatechange = function() {
            if(this.readyState == 4 & this.status == 200) {
                let result = this.responseText.trim();
                console.log(result);
                if(result == '1') {
                    alert('댓글이 삭제되었습니다.');
                    getList();
                } else {
                    alert('댓글삭제에 실패했습니다.');
                }
            }
        }
    }
})

getList();

function getList(){
    const xhttp = new XMLHttpRequest();

    xhttp.open("GET", "../noticeReply/list?num="+num.value);

    // GET 방식일 때는 생략 가능
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xhttp.send();

    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200) {
            replyResult.innerHTML = this.responseText.trim();
        }
    }
}

reply.addEventListener("click", function(){
    console.log(num.value);
    console.log(writer.value);
    console.log(contents.value);

    // JS에서 요청 객체를 생성(준비)
    const xhttp = new XMLHttpRequest();

    // 요청 정보 입력 (메서드 형식, URL)
    // get방식일 때는 여기 URL에 파라미터를 입력해서 보내야 함
    xhttp.open("POST", "../noticeReply/add");

    // 요청 header 정보 (form 태그의 enctype에 해당하는 정보임)
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    // 요청 전송
    // post 요청 시에는 여기에 파라미터를 보내야 함
    // send("이름1=값1&이름2=값2...") -> 하나의 문자열로 만들어서 보냄
    xhttp.send("num="+num.value+"&writer="+writer.value+"&contents="+contents.value);

    // 응답 처리 
    xhttp.onreadystatechange = function(){ // 이벤트임
        // 응답이 발생했을 때 이 함수를 실행할게요
        if(this.readyState == 4 && this.status == 200) {
            let result = this.responseText.trim();
            if (result=='1'){
                alert('댓글이 등록되었습니다.');
                getList(); // 등록하면 다시 댓글리스트를 가져오게
            } else {
                alert('댓글 등록에 실패했습니다.');
            }
        } 
    }
});
