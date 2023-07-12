package com.atemos.sample.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;

import com.atemos.sample.service.AuthService;
import com.atemos.sample.utils.JwtUtil;

//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//    
//    @Autowired
//    private CorsConfig corsConfig;
//    
//    @Autowired
//    private AuthService authService;
//     
//    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//        	.addFilter(corsConfig.corsFilter())
//            .csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .formLogin().disable()
//            .httpBasic().disable()
//            .authorizeRequests()
//                .antMatchers("/api/public").permitAll() // 공개 엔드포인트
//                .antMatchers("/api").authenticated(); // 보호된 엔드포인트  
//    } 
//}
 
@Configuration
@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private CorsConfig corsConfig;
    
    @Autowired
    private AuthService authService;
     
    @Bean
    public WebSecurityCustomizer configure() {
    	return (obj) -> obj.ignoring().antMatchers("/swagger-ui/**","/api/public/**","/api/public/**");
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//    	return http.antMatcher("/**").authorizeRequests();
    	 
    	 http.authorizeRequests()
    	 .antMatchers("/api/public/**").authenticated()
    	.anyRequest().permitAll()
//    	 .antMatchers("/api/public/**").permitAll()
//		.antMatchers("/api/v1/**").authenticated()	 
    	 .and()
    	 .cors().disable()
		.formLogin().disable()
		.httpBasic().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		;
	
    	 return http.build();
    }
    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//        	.addFilter(corsConfig.corsFilter())
//            .csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .formLogin().disable()
//            .httpBasic().disable()
//            .authorizeRequests()
//                .antMatchers("/api/public").permitAll() // 공개 엔드포인트
//                .antMatchers("/api").authenticated(); // 보호된 엔드포인트  
//    } 
}

