package ksmart38.mybatis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ksmart38.mybatis.interceptor.CommonInterceptor;
import ksmart38.mybatis.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	private final CommonInterceptor commonInterceptor;
	private final LoginInterceptor loginInterceptor; 
	
	public WebConfig(CommonInterceptor commonInterceptor, LoginInterceptor loginInterceptor) {
		this.commonInterceptor = commonInterceptor;
		this.loginInterceptor = loginInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/")
				.excludePathPatterns("/favicon.ico")
				.excludePathPatterns("/js/**")
				.excludePathPatterns("/css/**");
		
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/")
				.excludePathPatterns("/ajax/**")
				.excludePathPatterns("/favicon.ico")
				.excludePathPatterns("/css/**")
				.excludePathPatterns("/js/**")
				.excludePathPatterns("/addMember")
				.excludePathPatterns("/login")
				.excludePathPatterns("/logOut");
	}
	
	
	
	
	
	
}
