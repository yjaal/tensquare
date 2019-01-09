package win.iot4yj.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import win.iot4yj.search.dao.ArticleSearchDao;
import win.iot4yj.search.pojo.Article;
import win.iot4yj.search.service.ArticleSearchService;

/**
 * Created by yj on 2019/1/8.
 *
 * @author yj
 */
@Service
public class ArticleSearchServiceImpl implements ArticleSearchService {

	@Autowired
	private ArticleSearchDao articleSearchDao;


	//文章搜索
	public Page<Article> findByTitleLike(String keywords, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return articleSearchDao.findByTitleOrContentLike(keywords, keywords, pageRequest);
	}

	//增加文章
	public void add(Article article) {
		articleSearchDao.save(article);
	}
}