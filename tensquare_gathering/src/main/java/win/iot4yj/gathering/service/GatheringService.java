package win.iot4yj.gathering.service;

import org.springframework.data.domain.Page;
import win.iot4yj.gathering.pojo.Gathering;

import java.util.List;
import java.util.Map;

/**
 * Created by yj on 2019/1/4.
 *
 * @author yj
 */
public interface GatheringService {

	List<Gathering> findAll();

	Page<Gathering> findSearch(Map whereMap, int page, int size);

	List<Gathering> findSearch(Map whereMap);

	Gathering findById(String id);

	void add(Gathering gathering);

	void update(Gathering gathering);

	void deleteById(String id);
}
