const title = document.getElementById("title");
const contents = document.getElementById("contents");
const btn = document.getElementById("btn");
const frm = document.getElementById("frm");

let titleCheck = false;
let conCheck = false;

btn.addEventListener("click", function(){
    if (title.value != '') {
        titleCheck = true;
    }

    if (contents.value != '') {
        conCheck = true;
    }
    console.log(contents.value)
    console.log(contents.value.length)
    console.log(titleCheck);
    console.log(conCheck);

    if(titleCheck && conCheck) {
        frm.submit();
    } else {
        alert('제목이나 내용을 입력하세요');
    }
})
