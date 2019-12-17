package com.lc.base.exception;

import java.util.Map;

/**
 * 异常接口类
 */
public interface BaseException {

	/**
	 * 返回异常信息
	 * @return
	 */
	String getMsg();

	/**
	 * 返回异常编码
	 * @return
	 */
	int getCode();

	/**
	 * 返回数据
	 * @return
	 */
	default Map<String, Object> getParams() {
		return null;
	}

}
