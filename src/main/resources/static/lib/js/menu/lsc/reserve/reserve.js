document.addEventListener('DOMContentLoaded', () => {
    const SUB_MAP = {
        10: [
            {v:"11", t:"정밀 점검(전체)"},
            {v:"12", t:"브레이크 점검/교환"},
            {v:"13", t:"현가/서스펜션"}
        ],
        20: [
            {v:"21", t:"타이어 교체"},
            {v:"22", t:"타이어 펑크 수리"},
            {v:"23", t:"휠 얼라인먼트"}
        ],
        30: [
            {v:"31", t:"엔진오일 교환"},
            {v:"32", t:"미션오일 교환"},
            {v:"33", t:"오일 필터 교환"}
        ],
        40: [
            {v:"41", t:"배터리 진단"},
            {v:"42", t:"배터리 교체"}
        ],
        50: [
            {v:"51", t:"와이퍼 교환"},
            {v:"52", t:"전구/램프 교체"},
            {v:"53", t:"에어필터 교환"}
        ]
    };

    const categoryEl = document.getElementById('category');
    const subEl = document.getElementById('subcategory');
    const submitBtn = document.getElementById('submitBtn');
    const clearBtn = document.getElementById('clearBtn');
    const modalBackdrop = document.getElementById('modalBackdrop');
    const modalContent = document.getElementById('modalContent');
    const modalCancel = document.getElementById('modalCancel');
    const modalConfirm = document.getElementById('modalConfirm');
    const display = document.getElementById('display');
    const miniPreview = document.getElementById('miniPreview');
    const chips = document.querySelectorAll('.chip');

    categoryEl.addEventListener('change', () => {
        const key = categoryEl.value;
        subEl.innerHTML = '';
        const defaultOpt = document.createElement('option');
        defaultOpt.value = '';
        defaultOpt.textContent = key ? '세부항목을 선택하세요' : '대분류 선택 시 자동표시';
        subEl.appendChild(defaultOpt);

        if (key && SUB_MAP[key]) {
            SUB_MAP[key].forEach(o => {
                const opt = document.createElement('option');
                opt.value = o.v; opt.textContent = o.t;
                subEl.appendChild(opt);
            });
        }
    });

    chips.forEach(c => c.addEventListener('click', () => c.classList.toggle('selected')));

    clearBtn.addEventListener('click', () => {
        document.getElementById('reserveForm').reset();
        subEl.innerHTML = '<option value="">대분류 선택 시 자동표시</option>';
        chips.forEach(c => c.classList.remove('selected'));
        display.style.display = 'none';
        miniPreview.innerHTML = '';
    });

    submitBtn.addEventListener('click', () => {
        const data = readForm();
        const missing = [];
        if(!data.category) missing.push('대분류');
        if(!data.subcategory) missing.push('소분류');
        if(!data.date) missing.push('예약일자');
        if(!data.time) missing.push('예약시간');

        if (missing.length) {
            alert('다음 항목을 입력해 주세요: ' + missing.join(', '));
            return;
        }

        fillModal(data);
        showModal();
    });

    modalCancel.addEventListener('click', () => hideModal());
    modalBackdrop.addEventListener('click', e => {
        if (e.target === modalBackdrop) hideModal();
    });

    modalConfirm.addEventListener('click', () => {
        const data = readForm();
        hideModal();
        fillMiniPreview(data);
        display.style.display = 'block';
        alert('예약이 완료되었습니다.');
    });

    function readForm() {
        const selectedChips = [...document.querySelectorAll('.chip.selected')].map(c => c.textContent);
        return {
            category: categoryEl.options[categoryEl.selectedIndex]?.text || '',
            categoryVal: categoryEl.value || '',
            subcategory: subEl.options[subEl.selectedIndex]?.text || '',
            subVal: subEl.value || '',
            date: document.getElementById('date').value || '',
            time: document.getElementById('time').value || '',
            carModel: document.getElementById('carModel').value.trim(),
            requests: document.getElementById('requests').value.trim(),
            options: selectedChips
        };
    }

    function fillModal(d) {
        modalContent.innerHTML = '';
        const addItem = (label, value) => {
            const el = document.createElement('div');
            el.className = 'item';
            el.innerHTML = `<strong>${label}</strong><div>${value || '-'}</div>`;
            modalContent.appendChild(el);
        };
        addItem('서비스', `${d.category} · ${d.subcategory}`);
        addItem('예약 일시', d.date && d.time ? `${d.date} · ${d.time}` : (d.date || d.time));
        addItem('차종', d.carModel || '-');
        addItem('옵션', d.options.length ? d.options.join(', ') : '-');
        addItem('요청사항', d.requests || '-');
    }

    function fillMiniPreview(d) {
        miniPreview.innerHTML = '';
        const rows = [
            {k:'서비스', v:`${d.category} · ${d.subcategory}`},
            {k:'예약 일시', v:d.date && d.time ? `${d.date} · ${d.time}` : (d.date || d.time || '-')},
            {k:'차종', v:d.carModel || '-'},
            {k:'옵션', v:d.options.length ? d.options.join(', ') : '-'},
            {k:'요청사항', v:d.requests || '-'}
        ];
        rows.forEach(r => {
            const row = document.createElement('div');
            row.className = 'preview-row';
            row.innerHTML = `<div><small>${r.k}</small></div><div class="tempDesc">${escapeHtml(r.v)}</div>`;
            miniPreview.appendChild(row);
        });
    }

    function showModal() {
        modalBackdrop.style.display = 'flex';
        modalBackdrop.setAttribute('aria-hidden','false');
    }
    function hideModal() {
        modalBackdrop.style.display = 'none';
        modalBackdrop.setAttribute('aria-hidden','true');
    }

    function escapeHtml(str) {
        if(!str) return '';
        return String(str).replace(/[&<>"']/g, m =>
            ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[m])
        );
    }

    (function setMinDate() {
        const dateEl = document.getElementById('date');
        const t = new Date();
        const yyyy = t.getFullYear();
        const mm = String(t.getMonth()+1).padStart(2,'0');
        const dd = String(t.getDate()).padStart(2,'0');
        dateEl.min = `${yyyy}-${mm}-${dd}`;
    })();
});

window.addEventListener("load", () => {

    // 각 옵션별 input 대응
    const optInputs = [
        document.querySelector('input[name="rqstOpt1"]'), // 대체 차량 요청
        document.querySelector('input[name="rqstOpt2"]'), // 빠른 점검
        document.querySelector('input[name="rqstOpt3"]'), // 세차 포함
        document.querySelector('input[name="rqstOpt4"]')  // 부품 사전확인
    ];

    const chipEls = document.querySelectorAll("#quickOptions .chip");

    if (!chipEls.length) {
        console.warn("No chips found");
        return;
    }

    chipEls.forEach((chip, idx) => {

        chip.addEventListener("click", (e) => {
            e.stopImmediatePropagation && e.stopImmediatePropagation();

            // 선택 상태 토글
            const nowSelected = chip.classList.toggle("selected");

            // input에 Y 또는 "" 넣기
            if (optInputs[idx]) {
                optInputs[idx].value = nowSelected ? "Y" : "";
            }

            console.log(`rqstOpt${idx + 1}:`, optInputs[idx].value);
        }, true);

    });
});

function goSave() {
    event.preventDefault(); // 기본 동작 방지
    document.getElementById('reserve-frm').submit();
}
