package com.lc.base.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 运行期异常基类
 *
 * @see BaseException
 * @see Exception
 */
@Getter
@Setter
public abstract class BaseCheckedException extends Exception implements BaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected int code;

	public BaseCheckedException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public BaseCheckedException(int code, String format, Object... args) {
		super(String.format(format, args));
		this.code = code;
		this.msg = String.format(format, args);
	}

}
