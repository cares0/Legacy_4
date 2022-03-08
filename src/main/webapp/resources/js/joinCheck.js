// 1. 전체 동의를 누르면 전체 체크가 눌리기

const checkAll = document.getElementById('checkAll');
const check = document.getElementsByClassName('check');
const btn = document.getElementById('btn');

checkAll.addEventListener("click",function() {
    // if(checkAll.checked==true) {
    //     for(let checkBox of check) {
    //         checkBox.checked=true;
    //     }
    // } else {
    //     for(let checkBox of check) {
    //         checkBox.checked=false;
    //     }
    // }
    for(let checkBox of check) {
        checkBox.checked= checkAll.checked;
    }
})
//let checkNum = 0;

for(let checkBox of check) {
    checkBox.addEventListener("click",function(){
        let final = true;
        for(c of check) {
            if(!c.checked){ // 한번이라도 체크가 헤제라면
                final = false; // final을 false로
            }
        }
        checkAll.checked=final;

        // if(checkBox.checked == true) {
        //     checkNum++
        // } else {
        //     checkNum--
        // }
        // if(checkNum == 4) {
        //     checkAll.checked=true;
        // } else {
        //     checkAll.checked=false;
        // }
        // console.log(checkNum);
    })
}

btn.addEventListener("click", function(){
    // let c = true;
    // for(ch of check) {
    //     if(!ch.cheched){
    //         c = false;
    //     }
    // }
    
    if(checkAll.checked){
    location.href="./join"
    } else {
        alert("약관 동의가 필요합니다.")
    }

})


