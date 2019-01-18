package win.iot4yj.friend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import win.iot4yj.friend.interceptor.JwtInterceptor;

/**
 * 拦截器配置类
 * Created by yj on 2019/1/16.
 *
 * @author yj
 */
@Configuration
public class InterceptorConfig extends WebMvcAutoConfiguration{

	@Autowired
	private JwtInterceptor jwtInterceptor;

	public void addIntercptors(InterceptorRegistry registry) {
		//注册拦截器要声明拦截器对象和要拦截的请求
		registry.addInterceptor(jwtInterceptor)
				.addPathPatterns("/**")//放行所有
				.excludePathPatterns("/**/login/**");//登陆不要拦截
	}
}
