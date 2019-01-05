package win.iot4yj.gathering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * Created by yj on 2019/1/4.
 *
 * @author yj
 */
@SpringBootApplication
@EnableCaching//表示使用springboot的缓存，这个缓存较为简单，一般用于findById的场景
public class GatheringApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatheringApplication.class, args);
	}

	@Bean
	public IdWorker idWorker() {
		return new IdWorker(1, 1);
	}
}
