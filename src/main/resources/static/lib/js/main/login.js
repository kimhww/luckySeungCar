function goJoin() {
    event.preventDefault(); // 폼 서브밋 방지
    location.href = "/user/agreement";
}

function goFindId(){
    event.preventDefault(); // 폼 서브밋 방지
    location.href = "/login/findId";
}

function checkCapsLock(event)  {
    if (event.getModifierState("CapsLock")) {
        document.getElementById("message").innerText = "Caps Lock이 켜져 있습니다."
	} else {
		document.getElementById("message").innerText = ""
	}
}