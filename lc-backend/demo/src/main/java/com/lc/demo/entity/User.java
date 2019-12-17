package com.lc.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("t_user")
public class User {
	
	@TableField("id")
	private Long id;
	
	@TableField("name")
	private String name;
	
	@TableField("other")
	private String other;
}
