package com.lc.base.dto;

import java.util.Map;

import com.lc.base.exception.code.BaseExceptionCode;
import com.lc.base.exception.code.ExceptionCode;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 统一返回格式
 *
 * @author lucheng
 * @param <T>
 * @see ExceptionCode
 */
@Getter
@Setter
@Accessors(chain = true)
public class R<T> {

	/**
	 * 调用是否成功标识，0：成功，-1:系统繁忙，此时请开发者稍候再试 详情见[ExceptionCode]
	 */
	@ApiModelProperty(value = "响应编码:0/200-请求处理成功")
	private Integer code;

	/**
	 * 结果消息，如果调用成功，消息通常为空T
	 */
	@ApiModelProperty(value = "提示消息")
	private String msg;

	/**
	 * 响应数据
	 */
	@ApiModelProperty(value = "响应数据")
	private T content;

	/**
	 * 请求路径
	 */
	@ApiModelProperty(value = "请求路径")
	private String path;

	/**
	 * 附加数据
	 */
	@ApiModelProperty(value = "附加数据")
	private Map<String, Object> extra;

	public R(Integer code, T content, String msg) {
		this.code = code;
		this.msg = msg;
		this.content = content;
	}

	public static <E> R<E> result(int code, E data, String msg) {
		return new R<>(code, data, msg);
	}

	public static R<String> result() {
		return result(ExceptionCode.SUCCESS);
	}

	public static <E> R<E> result(E content) {
		return result(ExceptionCode.SUCCESS, content);
	}

	public static <E> R<E> result(BaseExceptionCode code) {
		return result(code, null);
	}

	public static <E> R<E> result(BaseExceptionCode code, E content) {
		return new R<>(code.getCode(), content, code.getMsg());
	}

	public static <E> R<E> timeout() {
		return result(ExceptionCode.TIMEOUT);
	}

}