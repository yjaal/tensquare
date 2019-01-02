package win.iot4yj.qa.service;

import org.springframework.data.domain.Page;
import win.iot4yj.qa.pojo.Reply;

import java.util.List;
import java.util.Map;

/**
 * Created by yj on 2019/1/2.
 *
 * @author yj
 */
public interface ReplyService {

	List<Reply> findAll();

	Page<Reply> findSearch(Map whereMap, int page, int size);

	List<Reply> findSearch(Map whereMap);

	Reply findById(String id);

	void add(Reply reply);

	void update(Reply reply);

	void deleteById(String id);
}
