package com.lc.base.exception;

/**
 * 非业务异常 用于在处理非业务逻辑时，进行抛出的异常。
 *
 * @see BaseCheckedException
 */
public class CommonException extends BaseCheckedException {

	private static final long serialVersionUID = 1L;

	public CommonException(int code, String message) {
		super(code, message);
	}

	public CommonException(int code, String format, Object... args) {
		super(code, String.format(format, args));
		this.code = code;
		this.msg = String.format(format, args);
	}

	public CommonException wrap(int code, String format, Object... args) {
		return new CommonException(code, format, args);
	}

	@Override
	public String toString() {
		return "CommonException [msg=" + msg + ", code=" + code + "]";
	}

}
