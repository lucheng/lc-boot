package com.lc.demo.manager;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.demo.dto.UserAddDTO;
import com.lc.demo.dto.UserGetDTO;
import com.lc.demo.entity.User;

/**
 * 通用业务
 * @author lucheng
 *
 */
public interface IUserManager extends IService<User>{
	
	boolean add(UserAddDTO dto);
	
	UserGetDTO get(Long id);
	
}
