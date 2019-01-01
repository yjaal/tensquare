package win.iot4yj.base.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import win.iot4yj.base.pojo.Label;
import win.iot4yj.base.service.LabelService;

import java.util.List;

@RestController
@CrossOrigin//跨域处理
@RequestMapping("label")
public class LabelController {

	@Autowired
	private LabelService labelService;

	@GetMapping
	public Result findAll() {
		return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
	}

	@GetMapping(value = "{labelId}")
	public Result findById(@PathVariable("labelId") String labelId) {
		return new Result(true, StatusCode.OK, "查询成功", labelService.findById(labelId));
	}

	@PostMapping
	public Result save(@RequestBody Label label) {
		labelService.save(label);
		return new Result(true, StatusCode.OK, "添加成功");
	}

	@RequestMapping(value = "{labelId}", method = RequestMethod.PUT)
	public Result update(@PathVariable String labelId, @RequestBody Label label) {
		label.setId(labelId);
		labelService.update(label);
		return new Result(true, StatusCode.OK, "更新成功");
	}

	@RequestMapping(value = "{labelId}", method = RequestMethod.DELETE)
	public Result deleteById(@PathVariable String labelId) {
		labelService.deleteById(labelId);
		return new Result(true, StatusCode.OK, "删除成功");
	}

	//条件查询，不分页
	@PostMapping(value = "search")
	public Result find(@RequestBody Label label) {
		List<Label> list = labelService.find1(label);
		return new Result(true, StatusCode.OK, "查询成功", list);
	}

	@PostMapping(value = "search/{page}/{size}")
	public Result pageQuery(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
		Page<Label> pageList = labelService.find(label, page, size);
		return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(pageList.getTotalElements(), pageList.getContent()));
	}
}
