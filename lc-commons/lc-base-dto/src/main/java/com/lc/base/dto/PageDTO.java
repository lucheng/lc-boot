package com.lc.base.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 分页返回格式
 *
 * @author lucheng
 * @param <T>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class PageDTO<T> {

	/** 第几页 */
	private long pageNo;

	/** 每页多少行 */
	private long pageSize;

	/** 总记录数 */
	private long total;

	/** 列表 */
	private List<T> list;

}
