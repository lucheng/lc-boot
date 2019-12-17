package com.lc.demo.dto;

import com.lc.base.dto.InputPageDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户-查询
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "用户-查询")
public class UserQuery extends InputPageDTO {

	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
	private String name;
}
