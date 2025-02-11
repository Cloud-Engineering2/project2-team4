function getUserIdFromToken(token) {

    try {
        let payload = JSON.parse(atob(token.split('.')[1])); // Base64 디코딩
        console.log("📌 디코딩된 JWT Payload:", payload);
        return payload.uid || null;
    } catch (error) {
        console.error("토큰 디코딩 실패:", error);
        return null;
    }
}

// function checkTokenAndGetUserId(){
//     // 📌 토큰 가져오기
//     let token = localStorage.getItem("token");
//     if (!token) {
//         alert("❌ 로그인 후 이용하세요!");
//         return;
//     }
//
//     // 📌 토큰에서 uid 추출
//     let userId = getUserIdFromToken(token);
//     if (!userId) {
//         alert("❌ 사용자 정보를 가져올 수 없습니다. 다시 로그인하세요!");
//         return;
//     }
//     return userId;
// }
