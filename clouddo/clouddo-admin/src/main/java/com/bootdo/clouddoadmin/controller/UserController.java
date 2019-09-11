package com.bootdo.clouddoadmin.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bootdo.clouddoadmin.domain.UserDO;
import com.bootdo.clouddoadmin.dto.UserDTO;
import com.bootdo.clouddoadmin.dto.do2dto.UserConvert;
import com.bootdo.clouddoadmin.service.RoleService;
import com.bootdo.clouddoadmin.service.UserService;
import com.bootdo.clouddoadmin.utils.SecuityUtils;
import com.bootdo.clouddocommon.annotation.Log;
import com.bootdo.clouddocommon.annotation.Permissions;
import com.bootdo.clouddocommon.dto.LoginUserDTO;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;

/**
 * 用户信息
 * 
 * @author bootdo
 */
@RequestMapping("/user")
@RestController
public class UserController extends BaseController {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	/**
	 * 登录的当前用户，前台需要验证用户登录的页面可以调用此方法
	 * 
	 * @return
	 */
	@GetMapping("/currentUser")
	LoginUserDTO currentUser() {
		LoginUserDTO loginUserDTO = new LoginUserDTO();
		loginUserDTO.setUserId(SecuityUtils.getCurrentUser().getId().toString());
		// loginUserDTO.setUsername(FilterContextHandler.getUsername());
		loginUserDTO.setUsername(SecuityUtils.getCurrentUser().getUsername());

		loginUserDTO.setName(SecuityUtils.getCurrentUser().getPassword());
		loginUserDTO.setName(SecuityUtils.getCurrentUser().getName());
		return loginUserDTO;
	}

	/**
	 * 根据用户id获取用户
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("{id}")
	R get(@PathVariable("id") Long id) {
		UserDTO userDTO = UserConvert.MAPPER.do2dto(userService.get(id));
		return R.ok().put("data", userDTO);
	}

	/**
	 * 分页查询用户
	 * 
	 * @param params
	 * @return
	 */
	// public
	@Log("获取用户列表")
	@GetMapping("/getUser")
	R listByPage(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		// List<UserDTO> userDTOS =
		// UserConvert.MAPPER.dos2dtos((userService.list(query)));
		List<UserDO> userDTOS = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(userDTOS, total);
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 增加用户
	 * 
	 * @param user
	 * @return
	 */	
	@Log("添加用户")
	@PostMapping("/addUser")
	R save(@RequestBody UserDO user) {
		// user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return R.operate(userService.save(user) > 0);
	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 */	
	@Log("修改用户")
	@PutMapping("/editUser")
	R update(@RequestBody UserDO user) {
		return R.operate(userService.update(user) > 0);
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@Permissions("删除用户权限")
	@Log("删除用户")
	@DeleteMapping("/deleteUser")
	R remove(Long id) {
		return R.operate(userService.remove(id) > 0);
	}

	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] userIds) {
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

	@PostMapping("/exits")
	@ResponseBody
	boolean exits(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exits(params);
	}

	@GetMapping("/tokenUser")
	public Principal user(Principal user) {
		return user;
	}
}
