package win.iot4yj.user.service;

import org.springframework.data.domain.Page;
import win.iot4yj.user.pojo.Admin;

import java.util.List;
import java.util.Map;

/**
 * Created by yj on 2019/1/11.
 *
 * @author yj
 */
public interface AdminService {

	Admin findByLoginnameAndPassword(String loginname, String password);

	List<Admin> findAll();

	Page<Admin> findSearch(Map whereMap, int page, int size);

	List<Admin> findSearch(Map whereMap);

	Admin findById(String id);

	void add(Admin admin);

	void update(Admin admin);

	void deleteById(String id);
}
