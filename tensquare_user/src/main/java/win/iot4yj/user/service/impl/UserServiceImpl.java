package win.iot4yj.user.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;
import win.iot4yj.user.dao.UserDao;
import win.iot4yj.user.pojo.User;
import win.iot4yj.user.service.UserService;

import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by yj on 2019/1/11.
 *
 * @author yj
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private IdWorker idWorker;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private BCryptPasswordEncoder encoder;

	//更新关注数
	@Transactional
	public void incFollowcount(String userid, int x) {
		userDao.incFollowcount(userid, x);
	}

	//更新粉丝数
	@Transactional
	public void incFanscount(String userid, int x) {
		userDao.incFanscount(userid, x);
	}

	/**
	 * 根据手机号和密码查询用户
	 *
	 * @param mobile
	 * @param password
	 * @return
	 */
	public User findByMobileAndPassword(String mobile, String password) {
		User user = userDao.findByMobile(mobile);
		if (!Objects.equals(null, user) && encoder.matches(password, user.getPassword())) {
			return user;
		} else {
			return null;
		}
	}

	/**
	 * 用户注册
	 */
	public void add(User user, String checkcode) {
		String syscode = (String) redisTemplate.opsForValue().get("smscode_" + user.getMobile());
		if (syscode == null) {
			throw new RuntimeException("请点击获取验证码");
		}
		if (!syscode.equals(checkcode)) {
			throw new RuntimeException("验证码不正确");
		}

		user.setId(idWorker.nextId() + "");
		//密码加密
		user.setPassword(encoder.encode(user.getPassword()));
		user.setFollowcount(0);//关注数
		user.setFanscount(0);//粉丝数
		user.setOnline(0L);//在线时长
		user.setRegdate(new Date());//注册日期
		user.setUpdatedate(new Date());//更新日期
		user.setLastdate(new Date());//最后登陆日期

		userDao.save(user);
	}

	/**
	 * 发送短信验证码测试,产生的注册码会存入到redis，注册的时候需要和redis中的注册码进行比较
	 */
	public void sendSms(String mobile) {
		//生成六位验证码
		String checkcode = RandomStringUtils.randomNumeric(6);

		//存入redis,过期时间设置
		redisTemplate.opsForValue().set("smscode_" + mobile, checkcode, 5, TimeUnit.MINUTES);

		//发送验证码和手机号到rabbitMQ
		Map<String, String> map = new HashMap<>();
		map.put("mobile", mobile);
		map.put("checkcode", checkcode + "");
		rabbitTemplate.convertAndSend("sms", map);
		System.out.println("checkcode: " + checkcode + ",mobile: " + mobile);
	}

	/**
	 * 查询全部列表
	 *
	 * @return
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}

	/**
	 * 条件查询+分页
	 *
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<User> findSearch(Map whereMap, int page, int size) {
		Specification<User> specification = createSpecification(whereMap);
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return userDao.findAll(specification, pageRequest);
	}

	/**
	 * 条件查询
	 *
	 * @param whereMap
	 * @return
	 */
	public List<User> findSearch(Map whereMap) {
		Specification<User> specification = createSpecification(whereMap);
		return userDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 *
	 * @param id
	 * @return
	 */
	public User findById(String id) {
		return userDao.findById(id).get();
	}

	/**
	 * 增加
	 *
	 * @param user
	 */
	public void add(User user) {
		user.setId(idWorker.nextId() + "");
		//密码加密
		String newpassword = encoder.encode(user.getPassword());//加密后的密码
		user.setPassword(newpassword);
		userDao.save(user);
	}

	/**
	 * 修改
	 *
	 * @param user
	 */
	public void update(User user) {
		userDao.save(user);
	}

	/**
	 * 删除
	 *
	 * @param id
	 */
	public void deleteById(String id) {
		userDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 *
	 * @param searchMap
	 * @return
	 */
	private Specification<User> createSpecification(Map searchMap) {

		return (root, query, cb) -> {
			List<Predicate> predicateList = new ArrayList<Predicate>();
			// ID
			if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
				predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
			}
			// 手机号码
			if (searchMap.get("mobile") != null && !"".equals(searchMap.get("mobile"))) {
				predicateList.add(cb.like(root.get("mobile").as(String.class), "%" + (String) searchMap.get("mobile") + "%"));
			}
			// 密码
			if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
				predicateList.add(cb.like(root.get("password").as(String.class), "%" + (String) searchMap.get("password") + "%"));
			}
			// 昵称
			if (searchMap.get("nickname") != null && !"".equals(searchMap.get("nickname"))) {
				predicateList.add(cb.like(root.get("nickname").as(String.class), "%" + (String) searchMap.get("nickname") + "%"));
			}
			// 性别
			if (searchMap.get("sex") != null && !"".equals(searchMap.get("sex"))) {
				predicateList.add(cb.like(root.get("sex").as(String.class), "%" + (String) searchMap.get("sex") + "%"));
			}
			// 头像
			if (searchMap.get("avatar") != null && !"".equals(searchMap.get("avatar"))) {
				predicateList.add(cb.like(root.get("avatar").as(String.class), "%" + (String) searchMap.get("avatar") + "%"));
			}
			// E-Mail
			if (searchMap.get("email") != null && !"".equals(searchMap.get("email"))) {
				predicateList.add(cb.like(root.get("email").as(String.class), "%" + (String) searchMap.get("email") + "%"));
			}
			// 兴趣
			if (searchMap.get("interest") != null && !"".equals(searchMap.get("interest"))) {
				predicateList.add(cb.like(root.get("interest").as(String.class), "%" + (String) searchMap.get("interest") + "%"));
			}
			// 个性
			if (searchMap.get("personality") != null && !"".equals(searchMap.get("personality"))) {
				predicateList.add(cb.like(root.get("personality").as(String.class), "%" + (String) searchMap.get("personality") + "%"));
			}
			return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
		};
	}
}
