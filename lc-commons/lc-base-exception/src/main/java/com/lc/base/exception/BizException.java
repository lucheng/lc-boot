package com.lc.base.exception;

import java.util.Map;

import com.lc.base.exception.code.BaseExceptionCode;
import com.lc.base.exception.code.ExceptionCode;

/**
 * 业务异常 用于在处理业务逻辑时，进行抛出的异常。
 *
 * @see BaseUncheckedException
 */
public class BizException extends BaseUncheckedException {

	private static final long serialVersionUID = 1L;

	public BizException(String msg) {
		super(ExceptionCode.BASE_VALID_PARAM.getCode(), msg);
	}

	public BizException(int code, String msg) {
		super(code, msg);
	}

	public BizException(int code, String msg, Map<String, Object> extra) {
		super(code, msg, extra);
	}

	public BizException(int code, String msg, Object... args) {
		super(code, msg, args);
	}

	/**
	 * 实例化异常
	 * @param code 自定义异常编码
	 * @param msg 自定义异常消息
	 * @param args 已定义异常参数
	 * @return
	 */
	public static BizException wrap(int code, String msg, Object... args) {
		return new BizException(code, msg, args);
	}

	public static BizException wrap(String msg, Object... args) {
		return new BizException(ExceptionCode.ERROR.getCode(), msg, args);
	}

	public static BizException validFail(String msg, Object... args) {
		return new BizException(ExceptionCode.BASE_VALID_PARAM.getCode(), msg, args);
	}

	public static BizException wrap(BaseExceptionCode ex) {
		return new BizException(ex.getCode(), ex.getMsg());
	}

	@Override
	public String toString() {
		return "BizException [msg=" + msg + ", code=" + code + "]";
	}

}
