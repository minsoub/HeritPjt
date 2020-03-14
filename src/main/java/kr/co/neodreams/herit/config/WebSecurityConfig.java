package kr.co.neodreams.herit.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
/**import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
*/
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {  // extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
/*	
	//@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				//.antMatchers("/admin", "/admin/*").authenticated()
				//.antMatchers("/user",  "/user/*").authenticated()
				.anyRequest().permitAll()
				.and()
			.formLogin()
				.loginPage("/admin/login")
				.permitAll()
				.defaultSuccessUrl("/admin/member/member_list")
				.and()
			.logout()
				.logoutUrl("/admin/logout")
				.logoutSuccessUrl("/admin/login")
				.invalidateHttpSession(true)
				.permitAll();
	}
	
	//@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(new BCryptPasswordEncoder())	// 해시 문자열을 이용한 암호화 방식
		;
	}
	

	//@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring().antMatchers("/css/**", "/images/**", "/js/**", "/fonts/**");
	}
	*/
}
