package com.lc.demo.manager;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.demo.dto.User2AddDTO;
import com.lc.demo.entity.User2;

public interface IUser2Manager extends IService<User2>{
	
	boolean add(User2AddDTO dto);
	
}
