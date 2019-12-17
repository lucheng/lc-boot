package com.lc.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.lc.base.dto.SuperEntityDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 新增
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserAddDTO", description = "User-新增")
public class UserAddDTO extends SuperEntityDTO<Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
	@NotEmpty(message = "姓名不能为空")
	private String name;

}
