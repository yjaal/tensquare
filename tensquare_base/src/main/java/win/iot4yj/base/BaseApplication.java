package win.iot4yj.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

@SpringBootApplication
public class BaseApplication {
	public static void main(String[] args) {
		//springboot将web.xml省掉了，由这个启动类去找spring-web包中的
		//META-INF.services文件中配置的servlet容器初始化类，然后进行启动
		SpringApplication.run(BaseApplication.class, args);
	}

	//id生成器
	@Bean
	public IdWorker idWorker(){
		return new IdWorker(1, 1);
	}
}
