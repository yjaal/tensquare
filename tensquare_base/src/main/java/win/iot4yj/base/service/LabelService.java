package win.iot4yj.base.service;

import org.springframework.data.domain.Page;
import win.iot4yj.base.pojo.Label;

import java.util.List;

public interface LabelService {
	List<Label> findAll();

	Label findById(String id);

	void save(Label label);

	void update(Label label);

	void deleteById(String id);

	List<Label> find(Label label);

	List<Label> find1(Label label);

	Page<Label> find(Label label, int page, int size);

	Page<Label> find1(Label label, int page, int size);
}
