package win.iot4yj.article.service;

import org.springframework.data.domain.Page;
import win.iot4yj.article.pojo.Column;

import java.util.List;
import java.util.Map;

/**
 * Created by yj on 2019/1/3.
 *
 * @author yj
 */
public interface ColumnService {

	void deleteById(String id);

	List<Column> findAll();


	/**
	 * 条件查询+分页
	 *
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Column> findSearch(Map whereMap, int page, int size);


	/**
	 * 条件查询
	 *
	 * @param whereMap
	 * @return
	 */
	List<Column> findSearch(Map whereMap);

	/**
	 * 根据ID查询实体
	 *
	 * @param id
	 * @return
	 */
	Column findById(String id);

	/**
	 * 增加
	 *
	 * @param column
	 */
	void add(Column column);

	/**
	 * 修改
	 *
	 * @param column
	 */
	void update(Column column);
}
