package win.iot4yj.article.service;

import win.iot4yj.article.pojo.Comment;

import java.util.List;

/**
 * Created by yj on 2019/1/3.
 *
 * @author yj
 */
public interface CommentService {

	//根据文章ID查询评论列表
	public List<Comment> findByArticleid(String articleid);

	//文章评论新增
	public void add(Comment comment);
}
