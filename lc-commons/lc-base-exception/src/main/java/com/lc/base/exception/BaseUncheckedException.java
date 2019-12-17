package com.lc.base.exception;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * 非运行期异常基类，所有自定义非运行时异常继承该类
 *
 * @see RuntimeException
 */
@Getter
@Setter
public abstract class BaseUncheckedException extends RuntimeException implements BaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected int code;

	/**
	 * 附件数据
	 */
	protected Map<String, Object> extra;

	public BaseUncheckedException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public BaseUncheckedException(int code, String msg, Map<String, Object> extra) {
		super(msg);
		this.code = code;
		this.msg = msg;
		this.extra = extra;
	}

	public BaseUncheckedException(int code, String format, Object... args) {
		super(String.format(format, args));
		this.code = code;
		this.msg = String.format(format, args);
	}

}
