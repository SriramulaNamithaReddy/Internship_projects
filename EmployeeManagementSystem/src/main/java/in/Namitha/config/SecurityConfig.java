package in.Namitha.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import in.Namitha.service.EmployeeServiceImpl;

@Configuration
public class SecurityConfig {
	private final EmployeeServiceImpl employeeServiceImpl;
	@Autowired
	public SecurityConfig(EmployeeServiceImpl employeeServiceImpl) {
		this.employeeServiceImpl=employeeServiceImpl;
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests()
			 .requestMatchers(HttpMethod.POST, "/employees/add").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.defaultSuccessUrl("/employees", true)
				.permitAll()
				.and()
			.logout()
				.permitAll();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		return http.build();
	}
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	 public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
		var encoder=passwordEncoder();
        var user = org.springframework.security.core.userdetails.User.withUsername("Admin")
                .password(encoder.encode("Password"))
                .roles("ADMIN")
                .build();
        return new org.springframework.security.provisioning.InMemoryUserDetailsManager(user);
    }
}
