package fi.sycks.surveytool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.sycks.surveytool.web.UserDetailServiceImpl;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	     http
	     .authorizeRequests().antMatchers("/css/**", "/api/**").permitAll() // Enable css when logged out
	     .and()
	     .authorizeRequests()
	       .anyRequest().authenticated()
	       .and()
	   .formLogin()
	       .loginPage("/login")
	       .defaultSuccessUrl("/index")
	       .permitAll()
	       .and()
	   .logout()
	       .permitAll().and().csrf().disable();
	 }
	
	 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}

