package win.iot4yj.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import win.iot4yj.qa.pojo.Problem;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

	@Query(value = "select * from tb_problem problem, tb_pl pl where problem.id = pl.problemid and pl.labelid = ? order by replytime desc", nativeQuery = true)
	Page<Problem> newlist(String labelid, Pageable pageable);

	@Query(value = "select * from tb_problem problem, tb_pl pl where problem.id = pl.problemid and pl.labelid = ? order by reply desc", nativeQuery = true)
	Page<Problem> hotlist(String labelid, Pageable pageable);

	@Query(value = "select * from tb_problem problem, tb_pl pl where problem.id = pl.problemid and pl.labelid = ? and problem.reply = 0 order by createtime desc", nativeQuery = true)
	Page<Problem> waitlist(String labelid, Pageable pageable);

	/**
	 * nativeQuery = true : 启用sql语句
	 * 根据标签ID查询最新问题列表,先查询对应标签下的所有问题id,然后根据回复时间排序
	 *
	 * @param labelId
	 * @param pageable
	 * @return
	 */
	@Query("select p from Problem p where id in(select problemid from Pl where labelid=?1) order by replytime desc")
	Page<Problem> findNewListByLabelId(String labelId, Pageable pageable);


	/**
	 * 根据标签ID查询热门问题列表,根据回复数量排序
	 *
	 * @param labelId
	 * @param pageable
	 * @return
	 */
	@Query("select p from Problem p where id in (select problemid from Pl where labelid=?1) order by reply desc")
	Page<Problem> findHotListByLabelId(String labelId, Pageable pageable);


	/**
	 * 根据标签ID查询等待问题列表,回复数量为0并根据最新创建时间排序
	 *
	 * @param labelId
	 * @param pageable
	 * @return
	 */
	@Query("select p from Problem p where id in (select problemid from Pl where labelid=?1) and reply=0 order by createtime desc")
	Page<Problem> findWaitListByLabelId(String labelId, Pageable pageable);
}
