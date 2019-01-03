package win.iot4yj.article.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;
import win.iot4yj.article.dao.CommentDao;
import win.iot4yj.article.pojo.Comment;
import win.iot4yj.article.service.CommentService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by yj on 2019/1/3.
 *
 * @author yj
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private IdWorker idWorker;


	//根据文章ID查询评论列表
	public List<Comment> findByArticleid(String articleid) {
		return commentDao.findByArticleid(articleid);
	}

	//文章评论新增
	public void add(Comment comment) {
		comment.set_id(idWorker.nextId() + "");
		commentDao.save(comment);
	}
}
