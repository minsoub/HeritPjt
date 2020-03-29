package kr.co.neodreams.herit.model;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Component
@ToString
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserInfo  implements Serializable {
	/**
	 * serial id
	 */
	private static final long serialVersionUID = 7908051509104295518L;
	
	private String id;
	private String name;
	private String auth_chk;
	// etc..
}
