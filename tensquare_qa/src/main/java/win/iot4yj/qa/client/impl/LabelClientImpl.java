package win.iot4yj.qa.client.impl;

import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;
import win.iot4yj.qa.client.LabelClient;

/**
 * Description:
 * date: 2018/12/4 20:51
 * author: loveLy
 */


//注意，我们如果单纯的想通过Feign去调用只需要接口就行，没必要实现
@Component
public class LabelClientImpl implements LabelClient {

	@Override
	public Result findById(String id) {
		return new Result(false, StatusCode.ERROR, "熔断器启动了");
	}
}