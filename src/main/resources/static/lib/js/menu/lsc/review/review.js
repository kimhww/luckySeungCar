/*function saveScore() {
    const fullScore = 10;
    const userScore = document.getElementById("userScore").value || 0;
    const reviewScore = userScore + "/" + fullScore;
    alert("평점을 저장하였습니다.");

    document.getElementById("display").innerHTML =
        '<div class="rating-box">' +
            '<span class="icon">🛠️</span>' +
            '<span>평점 ' + reviewScore + '</span>' +
        '</div>';

    document.getElementById("display").style.display = "block";
}*/

function goSave() {
    document.getElementById("review-frm").submit();
}

function goReviewForm(resvSqnc) {
    event.preventDefault(); // 기본 동작 방지
    var url = "/menu/lsc/review/reviewForm";
    var param = "?resvSqnc=" + resvSqnc;
    console.log('urlParam: ' + url+param)
    location.href = url+param;
}

document.addEventListener("DOMContentLoaded", () => {

    const frm = document.getElementById("review-frm");
    const submitBtn = document.getElementById("submitBtn");
    const clearBtn = document.getElementById("clearBtn");

    const modalBackdrop = document.getElementById("modalBackdrop");
    const modalContent = document.getElementById("modalContent");
    const modalCancel = document.getElementById("modalCancel");

    const miniPreview = document.getElementById("miniPreview");
    const display = document.getElementById("display");

    /* ---------------------------
       초기화 버튼
    ----------------------------*/
    clearBtn.addEventListener("click", () => {
        frm.reset();
        display.style.display = "none";
        miniPreview.innerHTML = "";
    });

    /* ---------------------------
       작성완료 버튼 → 모달 오픈
    ----------------------------*/
    submitBtn.addEventListener("click", () => {
        const score = document.getElementById("userScore").value;
        const desc = document.getElementById("requests").value.trim();

        const missing = [];
        if (!score) missing.push("평점");

        if (missing.length > 0) {
            alert("다음 항목을 입력해 주세요: " + missing.join(", "));
            return;
        }

        // 모달 내용 채우기
        fillModal(score, desc);
        showModal();
    });

    /* ---------------------------
       모달 닫기
    ----------------------------*/
    modalCancel.addEventListener("click", hideModal);

    modalBackdrop.addEventListener("click", (e) => {
        if (e.target === modalBackdrop) hideModal();
    });

    /* ---------------------------
        모달 내용 생성
    ----------------------------*/
    function fillModal(score, desc) {
        modalContent.innerHTML = "";

        const addItem = (title, value) => {
            const row = document.createElement("div");
            row.className = "item";
            row.innerHTML = `
                <strong>${title}</strong>
                <div>${escapeHtml(value || "-")}</div>
            `;
            modalContent.appendChild(row);
        };

        addItem("평점", score + " / 10");
        addItem("상세 리뷰", desc || "-");
    }

    /* ---------------------------
        간단 미니 프리뷰
    ----------------------------*/
    function fillMiniPreview(score, desc) {
        miniPreview.innerHTML = `
            <div class="preview-row">
                <div><small>평점</small></div>
                <div class="tempDesc">${escapeHtml(score + " / 10")}</div>
            </div>
            <div class="preview-row">
                <div><small>상세 리뷰</small></div>
                <div class="tempDesc">${escapeHtml(desc || "-")}</div>
            </div>
        `;
        display.style.display = "block";
    }

    /* ---------------------------
        모달 show / hide
    ----------------------------*/
    function showModal() {
        modalBackdrop.style.display = "flex";
        modalBackdrop.setAttribute("aria-hidden", "false");
    }

    function hideModal() {
        modalBackdrop.style.display = "none";
        modalBackdrop.setAttribute("aria-hidden", "true");
    }

    /* ---------------------------
        HTML escape 함수
    ----------------------------*/
    function escapeHtml(str) {
        if (!str) return '';
        return String(str).replace(/[&<>"']/g, m =>
            ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;' }[m])
        );
    }

    /* ---------------------------
        모달 내부 "확인 버튼" → 저장 실행
        (HTML에서 onclick="goSave()" 로 연결됨)
        + 미니 프리뷰 표시
    ----------------------------*/
    window.fillMiniPreviewAndSave = () => {
        const score = document.getElementById("userScore").value;
        const desc = document.getElementById("requests").value.trim();
        fillMiniPreview(score, desc);
        goSave();
    };
});