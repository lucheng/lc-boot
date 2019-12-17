package com.lc.base.exception.code;

/**
 * 异常编码标准
 *
 * @author lucheng
 */
public interface BaseExceptionCode {

	/**
	 * 异常编码
	 * @return
	 */
	int getCode();

	/**
	 * 异常消息
	 * @return
	 */
	String getMsg();

}
