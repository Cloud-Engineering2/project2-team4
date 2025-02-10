/* SecurityConfig.java
 * showU Service - 자랑
 * 로그인, 토근, path permit 관련 config
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.10
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창   2025.02.08    최초 작성 : SecurityConfig 작성
 * 배희창   2025.02.09    test url permit 열어 둠
 * 이홍비   2025.02.10    token test - authenticated() 설정
 * 배희창   2025.02.09    test url permit 열어둠
 * 배희창   2025.02.10    id pw 불일치시 401 반환
 * 이홍비   2025.02.10    securityFilterChain() - 정적 자원 허용 처리
 * ========================================================
 */

package showu.security;

import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final CorsFilter corsFilter;
    private final UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(List.of(authProvider));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/css/**", "/js/**", "/images/**", "/favicon.ico").permitAll() // 정적 자원 허용
                    .requestMatchers("/", "/login", "/signup", "/posttest", "/postget", "/postdelete").permitAll() // 얘는 정적 페이지 로그인 없이 가능하게 함
                    .requestMatchers("/api/login/**", "/api/signup/**").permitAll() // 얘가 있어야 권한 없이 로그인이랑 회원 가입 가능
                    // .requestMatchers("/api/**").permitAll() // 개발 중이라 전부 열어 놨는데 나중에 무조건 지워야 함. 안 지우면 클나요
                    .requestMatchers("/test").authenticated() // test 용 - 추후 삭제
                    .anyRequest().authenticated()
            )
                .exceptionHandling(e -> e.authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 상태 코드
                    response.setContentType("application/json; charset=UTF-8");
                }))
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

