package kr.co.neodreams.herit.config;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import kr.co.neodreams.herit.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginInterceptor implements  HandlerInterceptor {
	
	@Autowired
	private CommonUtil commonUtil;
	
	@SuppressWarnings("static-access")
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//log.info("Interceptor > preHandle");
		//log.info("client connect ip : " + commonUtil.getClientIpAddr(request));
		// 사용자 로그인 체크
		
		return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //log.info("Interceptor > postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {
        //log.info("Interceptor > afterCompletion" );
        // 사용자 접속 정보 저장
    }
}
