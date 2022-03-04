const pwResult = document.getElementById('pwResult');
const pw = document.getElementById('pw');
const pw2 = document.getElementById('pw2');
const pw2Result = document.getElementById('pw2Result');
const id = document.getElementById('id');
const idResult = document.getElementById('idResult');
const frm = document.getElementById('frm');
const btn = document.getElementById('btn');
const name = document.getElementById('name');
const email = document.getElementById('email');
const phone = document.getElementById('phone');

let idCheck = false; // id가 들어갔는지 확인용 (입력했으면 true)
let pwCheck = false; // pw가 일치하는지 확인용 (확인됐으면 true)
let phoneCheck = false; // phone이 들어갔는지 확인용
let emailCheck = false; // email이 들어갔는지 확인용
let nameCheck = false; // name이 들어갔는지 확인용

// 비밀번호 조건 검증
pw.addEventListener("keyup", function(){
    let result = "정상적인 비밀번호 입니다";
    if(pw.value.length < 8) {
        result = "8글자 이상 입력해주세요";
    } else if(pw.value.length > 13) {
        result = "12글자 이하로 입력해주세요" }

    pwResult.innerHTML=result;
})
// 아이디를 입력했는지 검증
id.onblur = function(){
    let message = "";
    if(id.value.length < 1) {
        message = "아이디를 입력해주세요";
        idCheck = false;
    } else {
        idCheck = true;
    }
    idResult.innerHTML=message;
}
// 비밀번호가 일치한지 검증
pw2.onkeyup = function() { // 블러도 가능
    let message = ""
    if(pw.value==pw2.value) {
        message = "비밀번호가 일치합니다";
        pwCheck = true;
    } else {
        message = "비밀번호가 일치하지 않습니다";
        pwCheck = false;
    }
    pw2Result.innerHTML = message;
}

// name.onblur = function() {
//     if(name.value != '') {
//         nameCheck = true;
//     }
// }
// email.onblur = function() {
//     if(email.value != '') {
//         emailCheck = true;
//     }
// }
// phone.onblur = function() {
//     if(phone.value != '') {
//         phoneCheck = true;
//     }
// }

btn.addEventListener("click", function() { 
    // 버튼을 일반 버튼으로 만든 다음
    // 조건에 맞으면 submit 이벤트를 실행하고,
    // 맞지 않으면 submit 이벤트를 실행하지 않도록 JS에서 검증을 함
    // 이름을 입력했는지 검증
    if(name.value != '') {
        nameCheck = true;
    }
    // 이메일을 입력했는지 검증
    if(email.value != ''){
        emailCheck = true;
    }
    // 전화번호를 입력했는지 검증
    if(phone.value != ''){
        phoneCheck = true;
    }
    if(idCheck && pwCheck && nameCheck && emailCheck && phoneCheck) { // 모든 값이 검증 완료되면
        frm.submit(); // 이벤트 강제 실행
    } else { // 검증이 안됐다면 알림창
        alert('필수요건을 확인하세요')
    }
})
// 따로 네임, 이메일, 폰 각각 블러이벤트 처리해서 불리언 값 넣어줘도 됨

pw.addEventListener("change", function(){
    pwCheck = false; // 일단 비밀번호 확인한거 false 넣어놓음
    pw2.value=''; // 다시 확인하라고 비밀번호 확인창을 지워버림
    pw2Result.innerHTML=''; // 비밀번호 일치 메시지가 없어지도록
    pw2.focus(); // 다시 확인창을 입력하도록 포커스 이벤트 강제 실행
})