/* UserService.java
 * showU Service - 자랑
 * 로그인, 회원가입 처리용 유저 서비스. 뼈대만 작성해놔서 수정해야함
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창   2025.02.08    최초 작성 : UserService 작성
 * ========================================================
 */

package showu.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import showu.security.JwtTokenProvider;
import showu.dto.LoginRequestDTO;
import showu.dto.UserDTO;
import showu.entity.User;
import showu.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public String signup(UserDTO userDTO) {
        System.out.println("🔥 회원가입 로직 실행됨!");

        if (userRepository.existsByUserId(userDTO.getUserId())) {
            throw new IllegalStateException("이미 존재하는 사용자 ID입니다.");
        }

        User user = User.of(userDTO.getUserId(), userDTO.getUserPw());

        userRepository.save(user);
        System.out.println("✅ 유저 저장 완료!");
        return "회원가입 성공!";
    }

    public String login(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getUserPw())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtTokenProvider.createToken(userDetails.getUsername());
    }
}
