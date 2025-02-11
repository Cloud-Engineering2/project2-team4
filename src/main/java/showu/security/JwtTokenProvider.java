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
 * 배희창   2025.02.10    token uid값으로 생성하게 수정
 * 배희창   2025.02.10    userIdFromToken부분 uid값으로 검증하게 에러처리 완료
 * 채혜송   2025.02.11    httpRequest에서 token 가져오도록 getToken 추가
 * ========================================================
 */

package showu.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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

    public String createToken(Long uid) {
        return JWT.create()
                .withClaim("uid", uid) 
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
            return String.valueOf(decodedJWT.getClaim("uid").asLong()); 
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
            System.out.println(secretKey);
            return false;
        }
    }
    
	public String getToken (HttpServletRequest request) {
	    // 쿠키에서 token 값 가져오기
	    String token = null;
	    Cookie[] cookies = request.getCookies();  // 모든 쿠키 가져오기

	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if ("token".equals(cookie.getName())) {  // 쿠키 이름이 'token'인지 확인
	                token = cookie.getValue();  // token 값 가져오기
	                break;
	            }
	        }
	    }

	    return token;
	}
}
