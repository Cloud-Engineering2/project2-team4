/* SecurityConfig.java
 * showU Service - 자랑
 * 로그인, 토근, path permit 관련 config
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.11
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
 * 채혜송   2025.02.10    정적 자원 허용 처리 - /icons
 * 김예린   2025.02.11    정적 페이지 허용 처리 - /postDetail (test)
<<<<<<< HEAD
 * 전익주   2025.02.11    /api/admin/** ADMIN 권한 필요하게 수정
=======
 * 배희창   2025.02.11    like 경로 허용
>>>>>>> feat/ms/front
 * ========================================================
 */

package showu.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

import lombok.RequiredArgsConstructor;

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
                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll() // 모든 GET 요청 허용 (맨 위 배치)
                        .requestMatchers(HttpMethod.POST, "/api/post/like/**").permitAll() // 좋아요 경로만 허용
                        .requestMatchers(HttpMethod.PATCH, "/api/post/like/**").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/icons/**", "/favicon.ico", "/postput").permitAll()
                        .requestMatchers("/", "/login", "/signup", "/posttest", "/postget", "/postdelete", "/postput", "/postdetail").permitAll()
                        .requestMatchers("/api/login/**", "/api/signup/**").permitAll()
                        .requestMatchers("/test").authenticated()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                ).addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
            return http.build();
    }
}