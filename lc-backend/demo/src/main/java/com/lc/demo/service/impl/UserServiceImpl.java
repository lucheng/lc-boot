package com.lc.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.demo.dto.UserAddDTO;
import com.lc.demo.dto.UserGetDTO;
import com.lc.demo.manager.IUserManager;
import com.lc.demo.mapper.UserMapper;
import com.lc.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserManager userManager;
	@Autowired
	private UserMapper userMapper; //可以直接调用mapper

	@Override
	public void save(UserAddDTO dto) {
		userManager.add(dto);
	}

	@Override
	public UserGetDTO get(Long id) {
		return userManager.get(id);
	}

}
