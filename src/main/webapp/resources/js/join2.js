// ID, PW, NAME, EMAIL, PHONE 필수 입력 사항

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
let pwCheck = false; // pw 길이 검증
let pwCompCheck = false; // pw가 일치하는지 확인용 (확인됐으면 true)
let phoneCheck = false; // phone이 들어갔는지 확인용
let emailCheck = false; // email이 들어갔는지 확인용
let nameCheck = false; // name이 들어갔는지 확인용

// pwCheck : pw 길이 검증
pw.onblur = function() {
    let message = "";
    if (pw.value.length < 8 ) {
        message = "8자 이상으로 입력하세요";
        pwCheck = false;
    } else if (pw.value.length > 12) {
        message = "12자 이하로 입력하세요"
        pwCheck = false;
    } else {
        pwCheck = true;
    }
    pwResult.innerHTML = message;
}
// pwCompCheck : pw 확인 검증
pw2.onblur = function() {
    let message = ""
    if(pw.value == pw2.value) {
        pwCompCheck = true;
        message = "비밀번호가 일치합니다";
    } else {
        pwCompCheck = false;
        pw2.value='';
        message = "비밀번호가 일치하지 않습니다";
    }
    pw2Result.innerHTML = message;
}
// pw변경시 다시 확인
pw.onchange = function() {
    pwCompCheck = false;
    pw2.value = '';
    pw2Result.innerHTML = '비밀번호가 일치하지 않습니다';
    pw2.focus();
}
// id검증
id.onblur = function() {
    let message = ""
    if(id.value == '') {
        message = "id는 필수입니다";
        idCheck = false;
    } else {
        idCheck = true;
    }
    idResult.innerHTML = message;
}
// name 검증
name.onblur = function() {
    if(name.value == '') {
        nameCheck = false; 
    } else {
        nameCheck = true;
    }
}
// phone 검증
phone.onblur = function() {
    if(phone.value == '') {
        phoneCheck = false;
    } else {
        phoneCheck = true;
    }
    console.log(phoneCheck);
}
// email 검증
email.onblur = function() {
    if(email.value == '') {
        emailCheck = false;
    } else {
        emailCheck = true;
    }
    console.log(emailCheck);
}
// 최종 제출 검증
btn.onclick = function() {
    if (idCheck && pwCheck && pwCompCheck && nameCheck && phoneCheck && emailCheck) {
        frm.submit();
    } else if(idCheck == false) {
        alert('id를 확인하세요');
        id.focus()
    } else if(pwCheck == false) {
        alert('pw를 확인하세요')
        pw.focus();
    } else if(pwCompCheck == false) {
        alert('pw가 일치하지 않습니다');
        pw.focus();
    } else if(nameCheck == false) {
        alert('이름을 확인하세요');
        name.focus();
    } else if(phoneCheck == false) {
        alert('전화번호를 확인하세요');
        phone.focus();
    } else {
        alert('이메일을 확인하세요');
        email.focus();
    }
}