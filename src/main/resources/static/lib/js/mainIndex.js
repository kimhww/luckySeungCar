function startLuckyRepair() {
    event.preventDefault(); // 폼 서브밋 방지
    location.href = "/menu";
}

function goLoginForm() {
    event.preventDefault(); // 폼 서브밋 방지
    location.href = "/login/loginForm"
}

function goLogout() {
    event.preventDefault(); // 폼 서브밋 방지
    location.href = "/login/logout"
}