package win.iot4yj.jjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Created by yj on 2019/1/15.
 * @author yj
 */
public class ParseJwtTest {
	public static void main(String[] args) {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_otJ0iLCJpYXQiOjE1NDc1NjA3NDV9.ryT0pbKCbifuvD9b2CVFD8mua0WzfVOcRPOeZeopgPg";
		Claims claims = Jwts.parser().setSigningKey("iot4yj").parseClaimsJws(token).getBody();
		System.out.println("id: " + claims.getId());
		System.out.println("subject: " + claims.getSubject());
		System.out.println("IssuedAt: " + claims.getIssuedAt());//登陆时间
		System.out.println("自定义: " + claims.get("my_key"));//登陆时间
	}
}
