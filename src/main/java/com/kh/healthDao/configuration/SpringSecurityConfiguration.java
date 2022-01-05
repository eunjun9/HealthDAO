package com.kh.healthDao.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kh.healthDao.member.model.service.MemberService;

/* 스프링 시큐리티 설정 활성화 + bean 등록 가능 */
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{
   
   private MemberService memberService;
   
   @Autowired
   public SpringSecurityConfiguration(MemberService memberService) {
      this.memberService = memberService;
   }

   /* 암호화에 사용될 객체 BCryptPasswordEncoder
    * - encode() : BCrypt 해싱 함수를 사용해서 비밀번호를 인코딩 해주는 메소드, salt를 지원하여 똑같은 비밀번호를 인코딩 하더라도 매번 다른 인코딩 된 문자열 반환
    * - matchers(rawPassword, encodePassword) : 사용자에 의해 제출된 비밀번호와 저장소에 저장 되어있는 비밀번호의 일치 여부를 확인해주는 메소드 */
   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
   
   /* 정적 리소스는 권한 없이도 접근 가능하게끔 무시할 경로 작성 */
   @Override
   public void configure(WebSecurity web) {
      web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/**");
   }
   
   /* HTTP 요청에 대한 설정 */
   @Override
   public void configure(HttpSecurity http) throws Exception {
      http
         /* csrf는 기본적으로 활성화 되어 있으므로 비활성화 처리
          * CSRF 공격이란
          * 사이트 간 요청 위조, 웹 애플리케이션 취약점 중 하나로
          * 사용자의 의지와 무관하게 공격자가 의도한 행위(등록/수정/삭제)를 특정 웹 사이트에 요청하도록 만드는 공격
          * CSRF 공격 방어를 위해 referrer 검증 => 요청 도메인이 일치하는지 검증함
          * Spring Security CSRF Token 사용 => 임의의 토큰 방급 후 자원에 대한 "변경" 요청일 경우 token 값 확인
          * 
          *  */
         //.csrf().disable()
         /* 요청에 대한 권한 체크 */
         .authorizeRequests()
            /* 요청 보안 수준의 세부적인 설정
             * "/menu/**"요청은 인증이 되어야 함을 명시 */         
            .antMatchers("/mypage/**").authenticated()
            /* hasRole 안의 값 앞에는 자동으로 ROLE_ 가 붙음 */
            .antMatchers("/mypage/**").hasRole("MEMBER")
            .antMatchers("/trainer/**").hasRole("TRAINER")
            /* "/admin/**"의 요청은 ROLE_ADMIN 권한을 가진 사람에게만 허용 */
            .antMatchers("/admin/**").hasRole("ADMIN")
            /* 그 외의 요청은 모두 허가함 - 게스트 사용자도 접근 가능 */
            .anyRequest().permitAll()
         .and()
            /* 로그인 설정 */
            .formLogin()
            /* 로그인 페이지 설정 */
            .loginPage("/common/header")
            /* 로그인 성공 시 랜딩 페이지 설정 */
            .successForwardUrl("/")
         .and()
            /* 로그아웃 설정 */
            .logout()
            /* 로그아웃 요청 주소 */
            .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
            /* JSESSIONID 쿠키 삭제 */
            .deleteCookies("JSESSIONID")
            /* 세션 만료 */
            .invalidateHttpSession(true)
            /* 로그아웃 성공 시 랜딩 페이지 */
            .logoutSuccessUrl("/")
         .and()
            .exceptionHandling()
            /* 인가 되지 않았을 때 - 권한이 없을 때 이동할 페이지 */
            .accessDeniedPage("/common/denied");
   }
   
   @Override
   public void configure(AuthenticationManagerBuilder auth) throws Exception{
      /* 로그인, 로그아웃은 MemberController에 작성하지 않고 스프링 시큐리티 모듈을 통해 처리 */
      /* 유저 인증을 위해 사용할 MemberService 등록, 사용하는 패스워드 인코딩 방식 설정 */
      auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	  // userDetailsService : 로그인, 로그아웃을 컨트롤러 없이 service로 보낸다
   }
   
   
}