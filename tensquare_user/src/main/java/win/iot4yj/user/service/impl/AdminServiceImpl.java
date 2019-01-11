package win.iot4yj.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.IdWorker;
import win.iot4yj.user.dao.AdminDao;
import win.iot4yj.user.pojo.Admin;
import win.iot4yj.user.service.AdminService;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yj on 2019/1/11.
 *
 * @author yj
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private IdWorker idWorker;

	@Autowired
	private BCryptPasswordEncoder encoder;


	/**
	 * 根据登录名和密码查询
	 *
	 * @param loginname
	 * @param password
	 * @return
	 */
	public Admin findByLoginnameAndPassword(String loginname, String password) {
		Admin admin = adminDao.findByLoginname(loginname);
		//如果传入的密码和数据库密码匹配
		if (admin != null && encoder.matches(password, admin.getPassword())) {
			return admin;
		} else {
			return null;
		}
	}

	/**
	 * 查询全部列表
	 *
	 * @return
	 */
	public List<Admin> findAll() {
		return adminDao.findAll();
	}

	/**
	 * 条件查询+分页
	 *
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Admin> findSearch(Map whereMap, int page, int size) {
		Specification<Admin> specification = createSpecification(whereMap);
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return adminDao.findAll(specification, pageRequest);
	}


	/**
	 * 条件查询
	 *
	 * @param whereMap
	 * @return
	 */
	public List<Admin> findSearch(Map whereMap) {
		Specification<Admin> specification = createSpecification(whereMap);
		return adminDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 *
	 * @param id
	 * @return
	 */
	public Admin findById(String id) {
		return adminDao.findById(id).get();
	}


	/**
	 * 增加
	 *
	 * @param admin
	 */
	public void add(Admin admin) {
		admin.setId(idWorker.nextId() + "");
		//密码加密
		String newpassword = encoder.encode(admin.getPassword());
		admin.setPassword(newpassword);
		adminDao.save(admin);
	}

	/**
	 * 修改
	 *
	 * @param admin
	 */
	public void update(Admin admin) {
		adminDao.save(admin);
	}

	/**
	 * 删除
	 *
	 * @param id
	 */
	public void deleteById(String id) {
		adminDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 *
	 * @param searchMap
	 * @return
	 */
	private Specification<Admin> createSpecification(Map searchMap) {

		return (root, query, cb) -> {
			List<Predicate> predicateList = new ArrayList<Predicate>();
			// ID
			if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
				predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
			}
			// 登陆名称
			if (searchMap.get("loginname") != null && !"".equals(searchMap.get("loginname"))) {
				predicateList.add(cb.like(root.get("loginname").as(String.class), "%" + (String) searchMap.get("loginname") + "%"));
			}
			// 密码
			if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
				predicateList.add(cb.like(root.get("password").as(String.class), "%" + (String) searchMap.get("password") + "%"));
			}
			// 状态
			if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
				predicateList.add(cb.like(root.get("state").as(String.class), "%" + (String) searchMap.get("state") + "%"));
			}

			return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

		};

	}
}
