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
 * ========================================================
 */

function logout() {
    const token = localStorage.getItem("token");

    if (!token) {
        console.error("토큰이 없습니다.");
        document.getElementById('responseText').innerText = "로그인된 상태가 아닙니다.";
        return;
    }

    fetch("/api/logout", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}` // "Bearer " 접두사 추가
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("❌ 로그아웃 중 오류가 발생했습니다 - !response.ok : " + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log(data.message);
            localStorage.removeItem("token"); // 로컬 스토리지에서 토큰 삭제
            document.getElementById('responseText').innerText = "로그아웃 되었습니다.";
        })
        .catch(error => {
            document.getElementById('responseText').innerText = error.message;
            console.error("❌ 로그아웃 중 오류가 발생했습니다.", error);
        });
}