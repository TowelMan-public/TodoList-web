package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.client.exception.LoginFailureException;
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
        try {
	        user.setTokenForServer(
	        		userDetailsServiceImp.loginAndReturnToken(username, password));
	        user.setUsername(username);
	        user.setPassword(DUMMY_PASSWORD);
        }catch(LoginFailureException e){
        	throw new BadCredentialsException(e.getMessage());
        }
        //認証情報を返す
        return user;
	}	
}
