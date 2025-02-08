/* JtwTokenProvider.java
 * showU Service - 자랑
 * 토근 생성, 암호화 관련 내용
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창   2025.02.08    최초 작성 : JtwTokenProvider 작성
 * ========================================================
 */

package showu.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {
    private final String secretKey = "your-secret-key-your-secret-key"; // 🔹 256비트 이상 추천
    private final long validityInMilliseconds = 3600000; // 1시간

    public String createToken(String userId) {
        return JWT.create()
                .withSubject(userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication getAuthentication(String token) {
        String userId = userIdFromToken(token);
        User userDetails = new User(userId, "", Collections.emptyList());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String userIdFromToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return null;
        }
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }
}
