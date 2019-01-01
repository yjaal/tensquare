package win.iot4yj.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import win.iot4yj.recruit.pojo.Recruit;

import java.util.List;

public interface RecruitDao extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {

	//这里查询名字中的6表示返回前6个，默认返回前一个，根据创建时间排序
	//where state = ? order by createtime desc
	List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state);

	//where state != ? order by createtime desc
	List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state);
}
