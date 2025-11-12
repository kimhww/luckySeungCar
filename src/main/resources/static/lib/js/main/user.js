function goJoinForm(event){
    event.preventDefault(); // 기본 동작 방지

    const requiredTerms = ['term1', 'term2'];

    const allAgreed = requiredTerms.every(termName => {
        const radios = document.getElementsByName(termName);
        return Array.from(radios).some(r => r.checked && r.value === 'agree');
    });

    if(!allAgreed){
        alert('동의하지 않은 필수 약관이 있습니다. 모든 필수 약관에 동의하여 주시길 바랍니다.');
        return;
    }

    location.href = '/user/joinForm';
 }

 function goJoinProc(){
     event.preventDefault();

     const userId = document.getElementById('user_idnt').value.trim();
     const pass = document.getElementById('pass_word').value.trim();
     const passChk = document.getElementById('pass_word_chck').value.trim();
     const name = document.getElementById('user_name').value.trim();

     let phone1 = document.getElementById('phone1').value;
     if(phone1 === 'custom') phone1 = document.getElementById('phone1Custom').value.trim();
     const phone2 = document.getElementById('phone2').value.trim();
     const phone3 = document.getElementById('phone3').value.trim();
     const phone = `${phone1}-${phone2}-${phone3}`;

     const email1 = document.getElementById('email1').value.trim();
     let email2 = document.getElementById('emailDomainSelect').value;
     if(email2 === 'custom') email2 = document.getElementById('email2Custom').value.trim();
     const email = `${email1}@${email2}`;

     document.getElementById('messageForPassWord').textContent = '';
     document.getElementById('messageForPassWordChck').textContent = '';

     if(userId === '' || pass === '' || passChk === '' || name === '' || phone2 === '' || phone3 === '' || email1 === '' || email2 === ''){
         alert('모든 필수 항목을 입력해주세요.');
         return;
     }

     const passRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,20}$/;
     if(!passRegex.test(pass)){
         document.getElementById('messageForPassWord').textContent = '비밀번호는 영문, 숫자, 특수문자를 포함한 8~20자리여야 합니다.';
         return;
     }

     if(pass !== passChk){
         document.getElementById('messageForPassWordChck').textContent = '비밀번호가 일치하지 않습니다.';
         return;
     }

     const phoneRegex = /^01[016789]-\d{3,4}-\d{4}$/;
     if(!phoneRegex.test(phone)){
         alert('전화번호 형식이 올바르지 않습니다. 예: 010-1234-5678');
         return;
     }

     const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
     if(!emailRegex.test(email)){
         alert('올바른 이메일 형식을 입력해주세요.');
         return;
     }

     alert('회원가입이 완료되었습니다!');
     if(mergeData()){
         document.getElementById('join-frm').submit();
     }
 }

 document.addEventListener("DOMContentLoaded", () => {
     const phone1 = document.getElementById("phone1");
     const phone1Custom = document.getElementById("phone1Custom");
     const emailDomainSelect = document.getElementById("emailDomainSelect");
     const email2Custom = document.getElementById("email2Custom");

     phone1.addEventListener("change", () => {
         if(phone1.value === "custom"){
             phone1Custom.classList.remove("hidden");
             phone1Custom.focus();
         } else {
             phone1Custom.classList.add("hidden");
             phone1Custom.value = '';
         }
     });

     emailDomainSelect.addEventListener("change", () => {
         if(emailDomainSelect.value === "custom"){
             email2Custom.classList.remove("hidden");
             email2Custom.focus();
         } else {
             email2Custom.classList.add("hidden");
             email2Custom.value = '';
         }
     });
 });

 function mergeData() {
     const phone1 = document.getElementById('phone1').value === 'custom' ? document.getElementById('phone1Custom').value.trim() : document.getElementById('phone1').value;
     const phone = `${phone1}-${document.getElementById('phone2').value.trim()}-${document.getElementById('phone3').value.trim()}`;
     const email1 = document.getElementById('email1').value.trim();
     const email2 = document.getElementById('emailDomainSelect').value === 'custom' ? document.getElementById('email2Custom').value.trim() : document.getElementById('emailDomainSelect').value;
     const email = `${email1}@${email2}`;

     let teleHandInput = document.createElement('input');
     teleHandInput.type = 'hidden';
     teleHandInput.name = 'teleHand';
     teleHandInput.value = phone;
     document.getElementById('join-frm').appendChild(teleHandInput);

     let elecMailInput = document.createElement('input');
     elecMailInput.type = 'hidden';
     elecMailInput.name = 'elecMail';
     elecMailInput.value = email;
     document.getElementById('join-frm').appendChild(elecMailInput);

     return true;
 }