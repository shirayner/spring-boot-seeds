package com.ray.study.springboot04swagger2.controller;

import com.ray.study.springboot04swagger2.model.User;
import com.ray.study.springboot04swagger2.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController
 *
 * @author shira 2019/04/28 15:45
 */
@Api(tags = {"用户管理接口"})
@RestController
@RequestMapping(value = "/users")     // 通过这里配置使下面的映射都在/users下
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * 获取用户列表：
	 * 		处理"/users/"的GET请求，用来获取用户列表
	 * 		还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
	 *
	 * @return
	 */
	@ApiOperation(value = "查询文章用户列表")
	@GetMapping("/")
	public List<User> list() {
		return userService.list();
	}


	/**
	 * 获取用户信息：
	 * 		处理"/users/{id}"的GET请求，用来获取url中id值的User信息
	 * 		url中的id可通过@PathVariable绑定到函数的参数中
	 * @param id id
	 * @return user
	 */
	@ApiOperation(value = "查询用户",  notes = "根据用户id查询用户，用户id需大于0")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	@GetMapping("/{id}")
	public User get(@PathVariable Long id) {
		return userService.get(id);
	}


	/**
	 * 创建用户：
	 *  	处理"/users/"的POST请求，用来创建User
	 * 		除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
	 * @param user user
	 * @return user
	 */
	@ApiOperation(value = "新增用户", notes="新增用户接口，用户id不用传，会自增")
	@ApiImplicitParam(name = "user", value = "用户信息实体", required = true, dataType = "User", paramType = "body")
	@PostMapping("/")
	public User insert(@RequestBody User user) {
		return userService.insert(user);
	}


	/** 更新用户
	 * 		处理"/users"的PUT请求，用来更新User信息
	 * @param user user
	 * @return user
	 */
	@ApiOperation(value = "更新用户")
	@ApiImplicitParam(name = "user", value = "用户信息实体", required = true, dataType = "User", paramType = "body")
	@PutMapping("/")
	public User update(@RequestBody User user) {
		return userService.update(user);
	}

	/**
	 * 删除用户
	 * 		处理"/users/{id}"的DELETE请求，用来删除User
	 * @param id id
	 * @return success
	 */
	@ApiOperation(value = "删除用户")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id) {
		boolean result = userService.delete(id);
		return "success";
	}

}
