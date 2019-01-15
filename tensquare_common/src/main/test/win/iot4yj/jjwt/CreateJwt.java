package win.iot4yj.jjwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Created by yj on 2019/1/15.
 *
 * @author yj
 */
public class CreateJwt {

	public static void main(String[] args) {
		JwtBuilder builder = Jwts.builder().setId("888")
				.setSubject("小贝")
				.setIssuedAt(new Date())//登陆时间
				.signWith(SignatureAlgorithm.HS256, "iot4yj")
				.setExpiration(new Date(new Date().getTime() + 3000))//设置过期时间
				.claim("my_key", "my_value");//自定义键值对
		System.out.println(builder.compact());
	}
}
