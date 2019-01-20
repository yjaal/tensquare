package win.iot4yj.qa.client;


import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import win.iot4yj.qa.client.impl.LabelClientImpl;

/**
 * Description:
 * date: 2018/12/4 16:41
 * author: loveLy
 */

//value的名字不能包含下划线，这里fallback表示如果feign调用失败，则去调用实现类中的方法
@FeignClient(value = "tensquare-base", fallback = LabelClientImpl.class)  //指定要调用的服务
public interface LabelClient {

	//指定微服务映射地址，这里value中的id一定要和@PathVariable中的id名字保持一直
	@RequestMapping(value = "/label/{id}", method = RequestMethod.GET)
	Result findById(@PathVariable("id") String id);
}
