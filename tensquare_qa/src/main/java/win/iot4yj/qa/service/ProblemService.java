package win.iot4yj.qa.service;

import org.springframework.data.domain.Page;
import win.iot4yj.qa.pojo.Problem;

import java.util.List;
import java.util.Map;

/**
 * Created by yj on 2019/1/2.
 *
 * @author yj
 */
public interface ProblemService {

	Page<Problem> newlist(String labelId, int page, int size);

	Page<Problem> hotlist(String labelId, int page, int size);

	Page<Problem> waitlist(String labelId, int page, int size);

	Page<Problem> findWaitListByLabelId(String labelId, int page, int size);

	Page<Problem> findHotListByLabelId(String labelId, int page, int size);

	Page<Problem> findNewListByLabelId(String labelId, int page, int size);

	List<Problem> findAll();

	Page<Problem> findSearch(Map whereMap, int page, int size);

	List<Problem> findSearch(Map whereMap);

	Problem findById(String id);

	void add(Problem problem);

	void update(Problem problem);

	void deleteById(String id);
}
