/* AuthController.java
 * showU Service - 자랑
 * 로그인 api 처리 컨트롤러
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창   2025.02.08    최초 작성 : AuthController 작성
 * 채혜송   2025.02.09    회원가입 수정 및 탈퇴 API 추가
 * ========================================================
 */
package showu.controller;

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

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody UserDTO userDTO) {

		try {
			User result = userService.signup(userDTO);
			return new ResponseEntity<>("성공적으로 회원가입 되었습니다..", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/deleteAccount")
	public ResponseEntity<String> deleteaccount(@RequestBody UserDTO userDTO) {

		try {
			userService.deleteAccount(userDTO);
			return new ResponseEntity<>("Success Delete Account", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
		String token = userService.login(loginRequest);
		return ResponseEntity.ok().body(token);
	}
}
