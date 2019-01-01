package win.iot4yj.recruit.service;

import org.springframework.data.domain.Page;
import win.iot4yj.recruit.pojo.Recruit;

import java.util.List;
import java.util.Map;

public interface RecruitService {

	List<Recruit> recommend();

	List<Recruit> newList();

	List<Recruit> findAll();

	Page<Recruit> findSearch(Map whereMap, int page, int size);

	List<Recruit> findSearch(Map whereMap);

	Recruit findById(String id);

	void add(Recruit recruit);

	void update(Recruit recruit);

	void deleteById(String id);
}
