package win.iot4yj.rabbit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import win.iot4yj.rabbitmq.RabbitApplication;

/**
 * Created by yj on 2019/1/11.
 *
 * @author yj
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitApplication.class)//制定启动类
public class ProduceTest {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	//直接模式
	@Test
	public void sendTest(){
		//向itcast这个队列中存入一条消息
		//直接模式就相当于不经过交换器直接将消息存入队列中
		rabbitTemplate.convertAndSend("itcast", "直接模式测试");
	}

	//分列模式
	@Test
	public void send1Test(){
		//通过交换器一次性向三个队列存入消息，第二个参数是一个绑定规则，暂时不填
		rabbitTemplate.convertAndSend("dandan", "", "分列模式测试");
	}

	//主题模式
	@Test
	public void send2Test(){
		//通过交换器一次性向三个队列存入消息，第二个参数是一个绑定规则
		rabbitTemplate.convertAndSend("dandan1", "good.abc", "主题模式测试");
	}
}
