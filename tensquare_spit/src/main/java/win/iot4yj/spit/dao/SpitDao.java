package win.iot4yj.spit.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import win.iot4yj.spit.pojo.Spit;

/**
 * Description:
 * date: 2018/11/28 9:29
 * author: loveLy
 */
public interface SpitDao extends MongoRepository<Spit, String> {

	//根据上级ID查询吐槽列表(分页)
	public Page<Spit> findByParentid(String parentid, Pageable pageable);

}
