package com.bootdo.clouddoadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		/*
		 * http.csrf().disable().exceptionHandling() .authenticationEntryPoint(
		 * (request, response, authException) ->
		 * response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
		 * .and().authorizeRequests().anyRequest().authenticated().and().httpBasic();
		 */
		
		http.authorizeRequests()
		 .anyRequest().permitAll() //任何请求,登录后可以访问
		 // 配置登录URI、登录失败跳转URI与登录成功后默认跳转URI
		 .and().formLogin().loginPage("/login").defaultSuccessUrl("/").successForwardUrl("/dashboard").failureForwardUrl("/fail").permitAll()
		 // 注销行为任意访问
		 .and().logout().permitAll()
		 // 设置拒绝访问的提示URI
		 .and().exceptionHandling().accessDeniedPage("/login?illegal")
		 .and().csrf().disable().anonymous().disable();
	
		
		
	}
}