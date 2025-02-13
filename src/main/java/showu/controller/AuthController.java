/* AuthController.java
 * showU Service - 자랑
 * 로그인 api 처리 컨트롤러
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.10
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창   2025.02.08    최초 작성 : AuthController 작성
 * 채혜송   2025.02.09    회원 가입 수정 및 탈퇴 API 추가
 * 배희창   2025.02.09    login() - 토큰 로컬 스토리지 저장 추가
 * 채혜송   2025.02.09    회원가입 수정 및 탈퇴 API 추가
 * 배희창   2025.02.10    login 부분 401 에러처리 수정
 * 채혜송   2025.02.10    회원가입 return 수정
 * 이홍비   2025.02.10    login() - try-catch 제거 // logout() 구현
 * 이홍비   2025.02.10    login() - nickname, userRole 추가
 * ========================================================
 */
package showu.controller;


import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import showu.dto.LoginRequestDTO;
import showu.dto.UserDTO;
import showu.entity.User;
import showu.service.UserService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody UserDTO userDTO) {

		try {
			User result = userService.signup(userDTO);
			return ResponseEntity.ok().body("Success Sign Up");
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}

	}

	@PostMapping("/deleteAccount")
	public ResponseEntity<String> deleteaccount(@RequestBody UserDTO userDTO) {

		try {
			userService.deleteAccount(userDTO);
			return ResponseEntity.ok().body("Success Delete Account");
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}


	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {

		System.out.println("🔥 로그인 도전! - Controller : " + loginRequest.getUserId() + ", " + loginRequest.getUserPw());

		String token = userService.login(loginRequest);

		System.out.println("✅ 로그인 성공! - Controller : " + token);

		UserDTO userDTO = userService.getUserById(loginRequest.getUserId());

		// 토큰 주입
		Map<String, String> response = new HashMap<>();
		response.put("token", token);
		response.put("nickname", userDTO.getNickname());
		response.put("role", userDTO.getUserRole().getUserRole());

		return ResponseEntity.ok(response);
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request) {

		System.out.println("🔥 로그아웃 도전! - Controller");

		String token = request.getHeader("Authorization"); // 토큰 get
		if (token != null && !token.isEmpty()) {
			// 토큰 존재 o
			System.out.println("🚪 로그아웃 성공! - Controller : " + token);

			return ResponseEntity.ok(Collections.singletonMap("message", "로그아웃 되었습니다."));
		} else {
			// 토큰 못 받음
			System.out.println("❌ 로그아웃 실패! - Controller : " + token);

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("message", "토큰이 제공되지 않았습니다."));
		}

	}


}
