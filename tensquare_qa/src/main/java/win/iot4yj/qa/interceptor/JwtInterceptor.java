package win.iot4yj.qa.interceptor;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 拦截器，用于校验用户权限
 * Created by yj on 2019/1/16.
 *
 * @author yj
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

	@Autowired
	private JwtUtil jwtUtil;

	//返回true就是放行
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//无论如何都放行，具体能不能进行操作还是在具体的操作中去判断
		//拦截器只是负责把请求头中包含的token进行一个解析
		//删除用户必须有管理员权限，这里之所以会这样校验，是和我们生成token自定义的规则是相配合的

		String header = request.getHeader("Authorization");
		if (!StringUtils.isEmpty(header) && !header.startsWith("Bearer ")) {
			//得到token
			String token = header.substring(7);//"bearer "长度为7
			try {
				Claims claims = jwtUtil.parseJWT(token);
				String roles = (String) claims.get("roles");
				if (!StringUtils.isEmpty(roles)) {
					if (Objects.equals("admin", roles)) {
						request.setAttribute("claim_admin", token);
					}else if(Objects.equals("user", roles)){
						request.setAttribute("claim_user", token);
					}
				}

			} catch (Exception e) {
				logger.error("令牌不正确或者可能过期： " + e);
				throw new RuntimeException("令牌不正确或者可能过期");
			}
		}
		return true;
	}

}
