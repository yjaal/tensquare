package win.iot4yj.base.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;
import win.iot4yj.base.dao.LabelDao;
import win.iot4yj.base.pojo.Label;
import win.iot4yj.base.service.LabelService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelServiceImpl implements LabelService {

	@Autowired
	private LabelDao labelDao;
	@Autowired
	private IdWorker idWorker;

	@Override
	public List<Label> findAll() {
		return labelDao.findAll();
	}

	@Override
	public Label findById(String id) {
		//这里不应该这样做，应该返回Optional
		//TODO
		return labelDao.findById(id).get();
	}

	@Override
	public void save(Label label) {
		label.setId(idWorker.nextId() + "");
		labelDao.save(label);
	}

	@Override
	public void update(Label label) {
		labelDao.save(label);
	}

	@Override
	public void deleteById(String id) {
		labelDao.deleteById(id);
	}

	//条件查询
	@Override
	public List<Label> find(Label label) {
		return labelDao.findAll(getPredicate(label));
	}

	private Specification getPredicate(Label label) {
		return new Specification<Label>() {
			//去除警告
			private static final long serialVersionUID = 7339568606811113709L;
			/**
			 * @param root 根对象，封装具体要查询的对象
			 * @param criteriaQuery 封装一些关键字，如group by等，但一般我们可以将这些信息写在sql中，所以该字段一般不用
			 * @param criteriaBuilder 封装条件对象
			 * @return
			 */
			@Override
			public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();
				if (!StringUtils.isEmpty(label.getLabelname())) {
					//类似：where labelname like '%label.getLabelname()%'
					list.add(criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%"));
				}
				if (!StringUtils.isEmpty(label.getState())) {
					list.add(criteriaBuilder.equal(root.get("state").as(String.class), label.getState()));
				}
				if (!StringUtils.isEmpty(label.getRecommend())) {
					list.add(criteriaBuilder.equal(root.get("recommend").as(String.class), label.getRecommend()));
				}
				return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

	//和find方法的功能一样呢，只是使用了工具类进行简化
	@Override
	public List<Label> find1(Label label) {
		return null;
	}

	//和find方法的功能一样呢，只是进行了分页
	@Override
	public Page<Label> find(Label label, int page, int size) {
		return labelDao.findAll(getPredicate(label), PageRequest.of(page - 1, size));
	}

	//和find方法的功能一样呢，只是使用了工具类进行简化
	@Override
	public Page<Label> find1(Label label, int page, int size) {
		return null;
	}
}
