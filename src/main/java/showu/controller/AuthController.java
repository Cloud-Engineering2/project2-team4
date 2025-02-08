package showu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import showu.dto.UserDTO;
import showu.service.UserService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
	private final UserService userService;
	
	@PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO userDTO) {
        String result = userService.signup(userDTO);
        return ResponseEntity.ok(result);
    }
	
}
