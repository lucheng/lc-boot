package com.lc.base.exception.code;

/**
 * 全局错误码 200以内为成功 200-999为通用错误，比如403,404,500 1000以上为业务错误代码
 */
public enum ExceptionCode implements BaseExceptionCode {

	// 系统相关 start
	ERROR(0, "系统异常"), SUCCESS(1, "成功"), ERROR404(2, "请求路径错误"), PARAM_EX(3, "参数类型解析异常"), SQL_EX(4, "运行SQL出现异常"),
	NULL_POINT_EX(5, "空指针异常"), ILLEGALA_ARGUMENT_EX(6, "无效参数异常"), MEDIA_TYPE_EX(7, "请求类型异常"),
	LOAD_RESOURCES_ERROR(8, "加载资源出错"), BASE_VALID_PARAM(9, "统一验证参数异常"), OPERATION_EX(10, "操作异常"), TIMEOUT(11, "请求超时"),
	PARAM_INVALID(101, "参数不合法"), NOT_FOUND(102, "不存在此资源"), ALREADY_EXISTS(103, "资源已经存在,不能重复"),
	PARAMS_NULL(104, "参数不能为空"), METHOD_NOT_ALLOWED(105, "不支持当前请求类型");

	private int code;

	private String msg;

	ExceptionCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	public ExceptionCode build(String msg, Object... param) {
		this.msg = String.format(msg, param);
		return this;
	}

	public ExceptionCode param(Object... param) {
		this.msg = String.format(msg, param);
		return this;
	}

}
