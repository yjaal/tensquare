package win.iot4yj.search.service;

import org.springframework.data.domain.Page;
import win.iot4yj.search.pojo.Article;

/**
 * Created by yj on 2019/1/8.
 *
 * @author yj
 */
public interface ArticleSearchService {
	Page<Article> findByTitleLike(String keywords, int page, int size);

	void add(Article article);
}
