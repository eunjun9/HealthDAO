package com.kh.healthDao.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String msg = "";
		
		if (exception instanceof AuthenticationServiceException) {
			msg = "존재하지 않는 사용자입니다.";
			
		} else if(exception instanceof BadCredentialsException) {
			msg = "아이디 또는 비밀번호가 틀립니다.";
			
		}

		setDefaultFailureUrl("/member/login?error=true&exception="+msg);

        super.onAuthenticationFailure(request, response, exception);
        
	}
	
}
