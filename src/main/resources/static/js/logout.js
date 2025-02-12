/*
 * logout.js
 * showU Service - 자랑
 * html 에서 공통적으로 사용할 logout() 관련 js 파일
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.10
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 이홍비   2025.02.10    최초 작성 : logout() 구현
 * 이홍비   2025.02.10    responseText 관련 조건문 추가
 * ========================================================
 */

function logout() {
    const token = localStorage.getItem("token");

    if (!token) {
        console.error("❌ 토큰이 없습니다.");
        alert("로그인된 상태가 아닙니다.");
        return;
    }

    fetch("/api/logout", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("❌ 로그아웃 요청 실패: " + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log("✔ 로그아웃 성공:", data.message);

            // 세션 스토리지 데이터 삭제
            sessionStorage.removeItem("token");
            sessionStorage.removeItem("nickname");
            sessionStorage.removeItem("role");

            console.log("✔ 로컬 스토리지 초기화 완료");

            // 🔥 `responseText`가 존재하면 설정
            let responseTextElement = document.getElementById('responseText');
            if (responseTextElement) {
                responseTextElement.innerText = "로그아웃 되었습니다.";
            }

            // 🚀 즉시 UI 변경 후 redirect
            showLoggedOutUI();
            window.location.href = "/";
        })
        .catch(error => {
            console.error("❌ 로그아웃 중 오류 발생:", error);

            let responseTextElement = document.getElementById('responseText');
            if (responseTextElement) {
                responseTextElement.innerText = error.message;
            }

            alert("로그아웃 중 오류가 발생했습니다.");
        });
}
