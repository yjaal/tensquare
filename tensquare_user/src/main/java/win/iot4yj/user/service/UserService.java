package win.iot4yj.user.service;

import org.springframework.data.domain.Page;
import win.iot4yj.user.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * Created by yj on 2019/1/11.
 *
 * @author yj
 */
public interface UserService {

	void incFollowcount(String userid, int x);

	void incFanscount(String userid, int x);

	User findByMobileAndPassword(String mobile, String password);

	void add(User user, String code);

	void sendSms(String mobile);

	List<User> findAll();

	Page<User> findSearch(Map whereMap, int page, int size);

	List<User> findSearch(Map whereMap);

	User findById(String id);

	void add(User user);

	void update(User user);

	void deleteById(String id);
}
