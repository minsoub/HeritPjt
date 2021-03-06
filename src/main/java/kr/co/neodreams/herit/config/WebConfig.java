package kr.co.neodreams.herit.config;

import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CSS, JS, Images 등의 Resource를 사용하기 위한 경로 지정 클래스
 * 해당 경로는 resources/static 내에 존재한다.
 * 해당 맵핑은 아래와 같이 접근 가능하다.
 * <script th:src="@{/js/jquery/3.3.1/js/jquery.min.js}"></script>
 * 
 * @author hist
 *
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class WebConfig implements WebMvcConfigurer{
	@Value("${file.uploadpath}")
	String uploadPath;
	
	@Autowired
	@Qualifier(value = "loginInterceptor")
	private LoginInterceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
				.addPathPatterns("/admin/**");
				//.excludePathPatterns("/xxxxx/**");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		System.out.println(uploadPath);
		
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("classpath:/static/resources/", "classpath:/META-INF/resources/");		
		registry.addResourceHandler("/img/**").addResourceLocations("file:/"+uploadPath+"/").setCachePeriod(0);		// upload directory setup
		//  <resources mapping="/img/**" location="지정한 업로드 폴더 절대경로" />
	}
	
	/*
	 * @Override public void addCorsMappings(CorsRegistry cr) { cr.addMapping("/**")
	 * .allowedOrigins("http://127.0.0.1:8080") // 허용할 주소 및 포트
	 * .allowedOrigins("http://localhost:8080"); // 허용할 주소 및 포트 }
	 */	
	//j-session 삭제
 /**   @Bean
    public ServletContextInitializer clearJsession() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
               servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
               SessionCookieConfig sessionCookieConfig=servletContext.getSessionCookieConfig();
               sessionCookieConfig.setHttpOnly(true);
            }
        };
    }  **/
    
	@Bean
	public ModelMapper modelMapper() 
	{
		return new ModelMapper();
	}
}
