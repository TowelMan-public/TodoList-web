package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.demo.UrlConfig;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String LOGIN_PAGE = UrlConfig.ROOT_URL + "/login";
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                //ログイン不要でアクセス可能に設定
                .antMatchers(LOGIN_PAGE,UrlConfig.ROOT_URL + "/signup").permitAll()
                //上記以外は直リンク禁止
                .anyRequest().authenticated()
            .and()
            .formLogin()
                //ログイン処理のパス
                .loginProcessingUrl(LOGIN_PAGE)
                //ログインページ
                .loginPage(LOGIN_PAGE)
                //ログインエラー時の遷移先 ※パラメーターに「error」を付与
                .failureUrl(LOGIN_PAGE)
                //ログイン成功時の遷移先
                .defaultSuccessUrl(UrlConfig.ROOT_URL + "/home",true)
                //ログイン時のキー：ユーザー名
                .usernameParameter("username")
                //ログイン時のパスワード
                .passwordParameter("password")
            .and()
            .logout()
            	.logoutUrl(UrlConfig.ROOT_URL + "/logout")
                //ログアウト時の遷移先 POSTでアクセス
                .logoutSuccessUrl(LOGIN_PAGE);
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        // 静的リソースに対するアクセスはセキュリティ設定を無視する
        web.ignoring().antMatchers("/style.css");
    }
	
	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		// 自前のAuthenticationProviderを使用する
		return new AuthenticationProviderImpl();
	}
}
