package showu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import showu.dto.UserDTO;
import showu.entity.User;
import showu.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String signup(UserDTO userDTO) {
        System.out.println("🔥 회원가입 로직 실행됨!");

        // 중복 체크 로직 추가
        if (userRepository.existsByUserId(userDTO.getUserId())) {
            throw new IllegalStateException("이미 존재하는 사용자 ID입니다.");
        }

        User user = User.of(userDTO.getUserId(), userDTO.getUserPw(), userDTO.getNickname());

        userRepository.save(user);
        System.out.println("✅ 유저 저장 완료!");
        return "회원가입 성공!";
    }
}

