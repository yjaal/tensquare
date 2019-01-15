package win.iot4yj.sms;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description:
 * date: 2018/12/2 1:00
 * author: loveLy
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

	@Autowired
	private SmsUtil smsUtil;

	@Value("${aliyun.sms.template_code}")
	private String templateCode;//模板编号

	@Value("${aliyun.sms.sign_name}")
	private String signName;//签名


	//发送短信，消费消息，消息是user模块存入rabbitmq中的，然后sms模块拿到消息发送给个人
	@RabbitHandler
	public void sendSms(Map<String, String> message) {
		try {
			smsUtil.sendSms(message.get("mobile"), templateCode, signName, "{\"checkcode\":" + message.get("checkcode") + "}");
		} catch (ClientException e) {
			e.printStackTrace();
		}
		System.out.println("手机号： " + message.get("mobile"));
		System.out.println("验证码： " + message.get("checkcode"));
	}
}
