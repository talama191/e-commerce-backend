package base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import base.filter.JwtAuthenticationFilter;
import base.security.AppUserDetailService;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * Security Configuration file
 * 
 * @author Huu Bang
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(	//Enable method level security base on annotations
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true
		)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AppUserDetailService appUserDetailService;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(appUserDetailService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
			.and()
			.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("/",
                    "/favicon.ico",
                    "/**/*.png",
                    "/**/*.gif",
                    "/**/*.svg",
                    "/**/*.jpg",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js","/swagger-ui/**", "/v3/api-docs/**")
            .permitAll()
            //"/api/auth/**"
			.antMatchers("/api/auth/**").permitAll()
			.antMatchers("/cart/**").permitAll()
			.antMatchers("/product/**").permitAll()
			.antMatchers("/category/**").permitAll()
			.antMatchers("/minio/**").permitAll()
			.antMatchers("/user-management/user/all").hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
	@Bean
	 public OpenAPI customOpenAPI() {
	   return new OpenAPI()
	          .components(new Components()
	          .addSecuritySchemes("bearer-key",
	          new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
	}
	
}
