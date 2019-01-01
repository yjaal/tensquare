package win.iot4yj.recruit.service;

import org.springframework.data.domain.Page;
import win.iot4yj.recruit.pojo.Enterprise;

import java.util.List;
import java.util.Map;

public interface EnterpriseService {

	List<Enterprise> hotlist(String ishot);

	List<Enterprise> findAll();

	Page<Enterprise> findSearch(Map whereMap, int page, int size);

	List<Enterprise> findSearch(Map whereMap);

	Enterprise findById(String id);

	void add(Enterprise enterprise);

	void update(Enterprise enterprise);

	void deleteById(String id);
}
