package com.lc.demo.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.demo.dto.User2AddDTO;
import com.lc.demo.dto.UserAddDTO;
import com.lc.demo.manager.IUser2Manager;
import com.lc.demo.manager.IUserManager;
import com.lc.demo.service.IUser2Service;

@Service
public class User2ServiceImpl implements IUser2Service {
	@Autowired
	private IUser2Manager user2Manager;
	@Autowired
	private IUserManager userManager;
	
	@Override
	public void save(User2AddDTO dto) {
		user2Manager.add(dto);
		UserAddDTO user = new UserAddDTO();
		BeanUtils.copyProperties(dto, user);
		userManager.add(user);
	}

}
