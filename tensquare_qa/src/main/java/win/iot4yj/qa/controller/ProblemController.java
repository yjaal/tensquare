package win.iot4yj.qa.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import win.iot4yj.qa.pojo.Problem;
import win.iot4yj.qa.service.ProblemService;

import java.util.Map;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;

//	@Autowired
//	private HttpServletRequest request;


	@GetMapping("label/{labelId}")
	public Result findLabelById(@PathVariable String labelId) {
//        Result result = labelClient.findById(labelid);
		return null;
	}

	//根据标签ID查询等待回答列表
	@GetMapping("waitlist/{labelId}/{page}/{size}")
	public Result findWaitListByLabelId(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
		//方式一
//		Page<Problem> pageList = problemService.findWaitListByLabelId(labelId, page, size);

		//方式二
		Page<Problem> pageList = problemService.waitlist(labelId, page, size);
		return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
	}

	//根据标签ID查询热门问题列表
	@GetMapping(value = "hotlist/{labelId}/{page}/{size}")
	public Result findHotListByLabelId(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
		//方式一
//		Page<Problem> pageList = problemService.findHotListByLabelId(labelId, page, size);

		//方式二
		Page<Problem> pageList = problemService.hotlist(labelId, page, size);
		return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
	}

	//根据标签ID查询最新问题列表
	@GetMapping("newlist/{labelId}/{page}/{size}")
	public Result findNewListByLabelId(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
		//方式一
//		Page<Problem> pageList = problemService.findNewListByLabelId(labelId, page, size);

		//方式二
		Page<Problem> pageList = problemService.newlist(labelId, page, size);
		return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
	}

	/**
	 * 查询全部数据
	 *
	 * @return
	 */
	@GetMapping
	public Result findAll() {
		return new Result(true, StatusCode.OK, "查询成功", problemService.findAll());
	}

	/**
	 * 根据ID查询
	 *
	 * @param id ID
	 * @return
	 */
	@GetMapping("{id}")
	public Result findById(@PathVariable String id) {
		return new Result(true, StatusCode.OK, "查询成功", problemService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 *
	 * @param searchMap 查询条件封装
	 * @param page      页码
	 * @param size      页大小
	 * @return 分页结果
	 */
	@PostMapping("search/{page}/{size}")
	public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return new Result(true, StatusCode.OK, "查询成功", new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()));
	}

	/**
	 * 根据条件查询
	 *
	 * @param searchMap
	 * @return
	 */
	@PostMapping("search")
	public Result findSearch(@RequestBody Map searchMap) {
		return new Result(true, StatusCode.OK, "查询成功", problemService.findSearch(searchMap));
	}

	/**
	 * 增加
	 *
	 * @param problem
	 */
	@PostMapping
	public Result add(@RequestBody Problem problem) {
		//发布问题之前验证权限
//        Claims claims = (Claims) request.getAttribute("user_claims");
//        if (claims==null) {
//            return new Result(false,StatusCode.ACCESSERROR,"无权发布");
//        }
		problemService.add(problem);
		return new Result(true, StatusCode.OK, "增加成功");
	}

	/**
	 * 修改
	 *
	 * @param problem
	 */
	@PutMapping("{id}")
	public Result update(@RequestBody Problem problem, @PathVariable String id) {
		problem.setId(id);
		problemService.update(problem);
		return new Result(true, StatusCode.OK, "修改成功");
	}

	/**
	 * 删除
	 *
	 * @param id
	 */
	@DeleteMapping("{id}")
	public Result delete(@PathVariable String id) {
		problemService.deleteById(id);
		return new Result(true, StatusCode.OK, "删除成功");
	}

}
