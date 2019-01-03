package win.iot4yj.article.service;

import org.springframework.data.domain.Page;
import win.iot4yj.article.pojo.Article;

import java.util.List;
import java.util.Map;

/**
 * Created by yj on 2019/1/3.
 *
 * @author yj
 */
public interface ArticleService {

	public int updateThumbup(String id);

	/**
	 * 添加事务
	 * 文章审核
	 *
	 * @param id
	 */
	public void examine(String id);

	/**
	 * 查询全部列表
	 *
	 * @return
	 */
	public List<Article> findAll();


	/**
	 * 条件查询+分页
	 *
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Article> findSearch(Map whereMap, int page, int size);


	/**
	 * 条件查询
	 *
	 * @param whereMap
	 * @return
	 */
	public List<Article> findSearch(Map whereMap);


	/**
	 * 根据ID查询实体 ,加入缓存
	 *
	 * @param id
	 * @return
	 */
	public Article findById(String id);

	/**
	 * 增加
	 *
	 * @param article
	 */
	public void add(Article article);

	/**
	 * 修改
	 *
	 * @param article
	 */
	public void update(Article article);

	/**
	 * 删除
	 *
	 * @param id
	 */
	public void deleteById(String id);
}
