package com.sienge.transporte.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = false, prePostEnabled = false)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("joao").password("t0ps3cr3t").roles("USER")
			.and().withUser("pedro").password("t0ps3cr3t").roles("USER", "MANAGER")
			.and().withUser("admin").password("t0ps3cr3t").roles("USER", "MANAGER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/swagger-ui.html", "/swagger-resources/**", "/tipoveiculos/**", "/tiporodovias/**", "/rodovias/**", "/veiculos/**", "/transportes/**").permitAll()
			.antMatchers("/aaa/**").hasAnyRole("USER")
			.antMatchers("/h2_console/**").hasAnyRole("MANAGER")
			.anyRequest().authenticated()
			.and().httpBasic().and().csrf().disable();
	}
}