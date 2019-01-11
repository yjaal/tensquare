package win.iot4yj.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by yj on 2019/1/11.
 *
 * @author yj
 */
@Component
@RabbitListener(queues = "itcast2")//指定消费哪个队列中的消息
public class Customer3 {

	@RabbitHandler
	public void getMsg(String msg) {
		System.out.print("分列模式消费消息(队列itcast2)：" + msg);
	}
}
