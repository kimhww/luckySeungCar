/* ============================== */
/*        헤더 내 메뉴바 제어        */
/* ============================== */
(function() {
    const menuIcon = document.querySelector(".menu-left img");
    const dropdown = document.getElementById("dropdownMenu");
    const profileIcon = document.querySelector(".profileBox");
    const profileDropdown = document.getElementById("profileDropdown");
    const hasSubMenus = document.querySelectorAll(".mainItem.hasSub");

    const transitionTime = 500; // CSS transition과 동일하게

    // 메뉴 닫기
    function closeDropdownMenu(callback) {
        if (!dropdown.classList.contains("open")) {
            if (callback) callback();
            return;
        }
        dropdown.classList.remove("open");
        // 소메뉴 닫기
        hasSubMenus.forEach(item => {
            item.classList.remove("open");
            const subMenu = item.querySelector(".subMenu");
            if (subMenu) subMenu.style.maxHeight = null;
        });
        if (callback) setTimeout(callback, transitionTime);
    }

    // 프로필 닫기
    function closeProfileMenu(callback) {
        if (!profileDropdown.classList.contains("open")) {
            if (callback) callback();
            return;
        }
        profileDropdown.classList.remove("open");
        if (callback) setTimeout(callback, transitionTime);
    }

    // 메뉴 아이콘 클릭
    if (menuIcon && dropdown) {
        menuIcon.addEventListener("click", () => {
            // 프로필 메뉴 닫고 메뉴 토글
            closeProfileMenu(() => {
                dropdown.classList.toggle("open");
            });
        });
    }

    // 소메뉴 토글 (대메뉴 클릭)
    hasSubMenus.forEach(item => {
        const labelBox = item.querySelector(".mainLabelBox");
        const subMenu = item.querySelector(".subMenu");

        labelBox.addEventListener("click", () => {
            const isOpen = item.classList.contains("open");
            if (!isOpen) {
                item.classList.add("open");
                subMenu.style.maxHeight = subMenu.scrollHeight + "px";
            } else {
                item.classList.remove("open");
                subMenu.style.maxHeight = null;
            }
        });
    });

    // 프로필 아이콘 클릭
    if (profileIcon && profileDropdown) {
        profileIcon.addEventListener("click", () => {
            // 메뉴 닫기 후 프로필 토글
            closeDropdownMenu(() => {
                profileDropdown.classList.toggle("open");
            });
        });
    }
})();