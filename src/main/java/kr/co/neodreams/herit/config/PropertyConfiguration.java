package kr.co.neodreams.herit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix="file.uploadpath")
public class PropertyConfiguration {
	private String uploadpath;
	
}
