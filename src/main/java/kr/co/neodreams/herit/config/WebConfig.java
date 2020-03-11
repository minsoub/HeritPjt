package kr.co.neodreams.herit.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
@EnableTransactionManagement
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/static/", "classpath:/META-INF/resources/");
	}
	
	@Bean
	public ModelMapper modelMapper() 
	{
		return new ModelMapper();
	}
}
