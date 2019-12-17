package com.lc.demo.manager.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.demo.dto.User2AddDTO;
import com.lc.demo.entity.User2;
import com.lc.demo.manager.IUser2Manager;
import com.lc.demo.mapper.User2Mapper;

@Service
public class User2ManagerImpl extends ServiceImpl<User2Mapper, User2> implements IUser2Manager {

	@Override
	public boolean add(User2AddDTO dto) {
		User2 user = new User2();
		BeanUtils.copyProperties(dto, user);
		save(user);
		return true;
	}
}
