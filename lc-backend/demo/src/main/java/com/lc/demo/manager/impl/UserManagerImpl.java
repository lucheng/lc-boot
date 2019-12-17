package com.lc.demo.manager.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.demo.cache.UserCache;
import com.lc.demo.dto.UserAddDTO;
import com.lc.demo.dto.UserGetDTO;
import com.lc.demo.entity.User;
import com.lc.demo.manager.IUserManager;
import com.lc.demo.mapper.UserMapper;

@Service
public class UserManagerImpl extends ServiceImpl<UserMapper, User> implements IUserManager {

	@Autowired
	private UserCache userCache;

	@Override
	public boolean add(UserAddDTO dto) {
		User user = new User();
		BeanUtils.copyProperties(dto, user);
		save(user);
		userCache.add(user);
		return true;
	}

	@Override
	public UserGetDTO get(Long id) {

		User user = userCache.get(id);
		if (user == null) {
			user = getById(id);
			userCache.add(user);
		}
		
		if(user==null) {
			return null;
		}
		
		UserGetDTO dto = new UserGetDTO();
		BeanUtils.copyProperties(dto, user);
		return dto;
	}
}
