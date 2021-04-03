package com.example.demo.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.service.UserDetailsServiceImp;

public class AuthenticationProviderImpl extends AbstractUserDetailsAuthenticationProvider {
	@Autowired
	UserDetailsServiceImp userDetailsServiceImp;

	private static final String DUMMY_PASSWORD = "DUMMY_PASSWORD"; //認証では使用しないため、値は何でもよい(nullや空文字列はNG)
	
	@Override
	//何もしない
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)	throws AuthenticationException {
	    String password = (String) authentication.getCredentials();
	    
	    //入力ﾁｪｯｸ
        if ("".equals(username) || "".equals(password))  {
            // 例外はSpringSecurityにあったものを適当に使用
            throw new AuthenticationCredentialsNotFoundException("ログイン情報に不備があります。");
        }
        
        //認証及び認証情報の作成
        UserDetailsImp user = new UserDetailsImp();
        user.setTokenForServer(
        		userDetailsServiceImp.loginAndReturnToken(username, password));
        user.setUsername(username);
        user.setPassword(DUMMY_PASSWORD);
        
        //認証情報を返す
        return user;
	}	
}
