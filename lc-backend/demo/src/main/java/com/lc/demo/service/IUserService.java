package com.lc.demo.service;

import com.lc.demo.dto.UserAddDTO;
import com.lc.demo.dto.UserGetDTO;

public interface IUserService {
	
	void save(UserAddDTO addDto);

	UserGetDTO get(Long id);
}
