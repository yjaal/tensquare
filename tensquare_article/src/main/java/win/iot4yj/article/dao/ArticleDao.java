package win.iot4yj.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import win.iot4yj.article.pojo.Article;


/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

	/**
	 * 所有非查询操作都要加 @Modifying注解
	 * 文章审核，这里的"?1"表示第一个参数
	 *
	 * @param id
	 */
	@Modifying
	@Query(value = "update tb_article set state = 1 where id = ?1", nativeQuery = true)
	void examine(String id);

	/**
	 * 点赞
	 * 这里要注意thumbup不能为空，数据库中null + 1 = null
	 * @param id
	 * @return
	 */
	@Modifying
	@Query(value = "update tb_article set thumbup = thumbup + 1 where id = ?1", nativeQuery = true)
	int updateThumbup(String id);

}
