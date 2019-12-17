package com.lc.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lc.base.dto.R;
import com.lc.demo.dto.UserAddDTO;
import com.lc.demo.dto.UserGetDTO;
import com.lc.demo.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	protected IUserService userService;

	@ApiOperation(value = "添加")
	@PostMapping
	public R<String> save(@Valid UserAddDTO dto) {
		userService.save(dto);
		return R.result();
	}

	@ApiOperation(value = "查询")
	@GetMapping("/{id}")
	public R<UserGetDTO> get(@PathVariable Long id) {
		return R.result(userService.get(id));
	}
}