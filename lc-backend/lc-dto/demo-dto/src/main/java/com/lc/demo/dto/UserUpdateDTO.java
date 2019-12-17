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
 * 用户-修改
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "用户-修改",parent = SuperEntityDTO.class)
public class UserUpdateDTO extends SuperEntityDTO<Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名", required = true)
	@NotEmpty(message = "姓名")
	private String name;

}
