package win.iot4yj.spit.service;

import org.springframework.data.domain.Page;
import win.iot4yj.spit.pojo.Spit;

import java.util.List;

/**
 * Created by yj on 2019/1/6.
 *
 * @author yj
 */
public interface SpitService {

	void updateThumbup(String id);

	Page<Spit> findByParentid(String parentid, int page, int size);

	List<Spit> findAll();

	Spit findById(String id);

	void add(Spit spit);

	void update(Spit spit);

	void deleteById(String id);

}
