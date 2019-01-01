package win.iot4yj.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import win.iot4yj.recruit.pojo.Enterprise;

import java.util.List;

public interface EnterpriseDao extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise> {
	//查询热门企业列表，这就等同于where ishot = ?
	List<Enterprise> findByIshot(String ishot);
}
