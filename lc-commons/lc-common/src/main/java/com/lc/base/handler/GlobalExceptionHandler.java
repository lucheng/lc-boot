package com.lc.base.handler;

import static com.lc.utils.StrPool.EMPTY;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.ibatis.exceptions.PersistenceException;
import com.lc.base.dto.R;
import com.lc.base.exception.BizException;
import com.lc.base.exception.code.ExceptionCode;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常统一处理
 *
 * @author lucheng
 */
@ControllerAdvice(annotations = { RestController.class, Controller.class })
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(BizException.class)
	public R<String> bizException(BizException ex, HttpServletRequest request) {
		if(ex.getCode()==ExceptionCode.ERROR.getCode()) {
			log.error("BizException:", ex);
		}else {
			log.warn("BizException:", ex);
		}
		return R.result(ex.getCode(), EMPTY, ex.getMessage()).setExtra(ex.getParams()).setPath(request.getRequestURI());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public R<?> httpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
		log.warn("HttpMessageNotReadableException:", ex);
		String message = ex.getMessage();
		if (StrUtil.containsAny(message, "Could not read document:")) {
			String msg = String.format("无法正确的解析json类型的参数：%s",
					StrUtil.subBetween(message, "Could not read document:", " at "));
			return R.result(ExceptionCode.PARAM_EX.getCode(), EMPTY, msg).setPath(request.getRequestURI());
		}
		return R.result(ExceptionCode.PARAM_EX).setPath(request.getRequestURI());
	}

	@ExceptionHandler(BindException.class)
	public R<?> bindException(BindException ex, HttpServletRequest request) {
		log.warn("BindException:", ex);
		try {
			String msgs = ex.getBindingResult().getFieldError().getDefaultMessage();
			if (StrUtil.isNotEmpty(msgs)) {
				return R.result(ExceptionCode.PARAM_EX.getCode(), EMPTY, msgs).setPath(request.getRequestURI());
			}
		}
		catch (Exception ee) {
		}
		StringBuilder msg = new StringBuilder();
		List<FieldError> fieldErrors = ex.getFieldErrors();
		fieldErrors.forEach((oe) -> msg.append("参数:[").append(oe.getObjectName()).append(".").append(oe.getField())
				.append("]的传入值:[").append(oe.getRejectedValue()).append("]与预期的字段类型不匹配."));
		return R.result(ExceptionCode.PARAM_EX.getCode(), EMPTY, msg.toString()).setPath(request.getRequestURI());
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public R<?> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
			HttpServletRequest request) {
		log.warn("MethodArgumentTypeMismatchException:", ex);
		MethodArgumentTypeMismatchException eee = (MethodArgumentTypeMismatchException) ex;
		StringBuilder msg = new StringBuilder("参数：[").append(eee.getName()).append("]的传入值：[").append(eee.getValue())
				.append("]与预期的字段类型：[").append(eee.getRequiredType().getName()).append("]不匹配");
		return R.result(ExceptionCode.PARAM_EX.getCode(), EMPTY, msg.toString()).setPath(request.getRequestURI());
	}

	@ExceptionHandler(IllegalStateException.class)
	public R<?> illegalStateException(IllegalStateException ex, HttpServletRequest request) {
		log.warn("IllegalStateException:", ex);
		return R.result(ExceptionCode.ILLEGALA_ARGUMENT_EX).setPath(request.getRequestURI());
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public R<?> missingServletRequestParameterException(MissingServletRequestParameterException ex,
			HttpServletRequest request) {
		log.warn("MissingServletRequestParameterException:", ex);
		StringBuilder msg = new StringBuilder();
		msg.append("缺少必须的[").append(ex.getParameterType()).append("]类型的参数[").append(ex.getParameterName()).append("]");
		return R.result(ExceptionCode.ILLEGALA_ARGUMENT_EX.getCode(), EMPTY, msg.toString())
				.setPath(request.getRequestURI());
	}

	@ExceptionHandler(NullPointerException.class)
	public R<?> nullPointerException(NullPointerException ex, HttpServletRequest request) {
		log.warn("NullPointerException:", ex);
		return R.result(ExceptionCode.NULL_POINT_EX).setPath(request.getRequestURI());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public R<?> illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
		log.warn("IllegalArgumentException:", ex);
		return R.result(ExceptionCode.ILLEGALA_ARGUMENT_EX).setPath(request.getRequestURI());
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public R<?> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {
		log.warn("HttpMediaTypeNotSupportedException:", ex);
		MediaType contentType = ex.getContentType();
		if (contentType != null) {
			StringBuilder msg = new StringBuilder();
			msg.append("请求类型(Content-Type)[").append(contentType.toString()).append("] 与实际接口的请求类型不匹配");
			return R.result(ExceptionCode.MEDIA_TYPE_EX.getCode(), EMPTY, msg.toString())
					.setPath(request.getRequestURI());
		}
		return R.result(ExceptionCode.MEDIA_TYPE_EX.getCode(), EMPTY, "无效的Content-Type类型")
				.setPath(request.getRequestURI());
	}

	/**
	 * jsr 规范中的验证异常
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public R<String> constraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
		log.warn("ConstraintViolationException:", ex);
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
		return R.result(ExceptionCode.BASE_VALID_PARAM.getCode(), EMPTY, message).setPath(request.getRequestURI());
	}

	/**
	 * spring 封装的参数验证异常， 在conttoller中没有写result参数时，会进入
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Object methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		log.warn("MethodArgumentNotValidException:", ex);
		final List<String> errList = new ArrayList<>();
		ex.getBindingResult().getAllErrors().stream().forEach(x -> {
			errList.add(x.getDefaultMessage());
		});
		return R.result(ExceptionCode.BASE_VALID_PARAM.getCode(), EMPTY, errList.toString())
				.setPath(request.getRequestURI());
	}

	/**
	 * 其他异常
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public R<?> otherExceptionHandler(Exception ex, HttpServletRequest request) {
		if (ex.getCause() instanceof BizException) {
			return bizException((BizException) ex.getCause(), request);
		}
		else {
			log.error("Exception:", ex);
		}
		return R.result(ExceptionCode.ERROR).setPath(request.getRequestURI());
	}

	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	public R<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex,
			HttpServletRequest request) {
		log.warn("HttpRequestMethodNotSupportedException:", ex);
		return R.result(ExceptionCode.METHOD_NOT_ALLOWED.getCode()).setPath(request.getRequestURI());
	}

	@ExceptionHandler(PersistenceException.class)
	public R<?> persistenceException(PersistenceException ex, HttpServletRequest request) {
		if (ex.getCause() instanceof BizException) {
			BizException cause = (BizException) ex.getCause();
			return R.result(cause.getCode(), EMPTY, cause.getMessage());
		}
		else {
			log.warn("PersistenceException:", ex);
		}
		return R.result(ExceptionCode.SQL_EX.getCode()).setPath(request.getRequestURI());
	}

	@ExceptionHandler(MyBatisSystemException.class)
	public R<?> myBatisSystemException(MyBatisSystemException ex, HttpServletRequest request) {
		if (ex.getCause() instanceof PersistenceException) {
			return persistenceException((PersistenceException) ex.getCause(), request);
		}
		else {
			log.error("MyBatisSystemException:", ex);
		}
		return R.result(ExceptionCode.SQL_EX).setPath(request.getRequestURI());
	}

	@ExceptionHandler(SQLException.class)
	public R<?> sqlException(SQLException ex, HttpServletRequest request) {
		log.error("SQLException:", ex);
		return R.result(ExceptionCode.SQL_EX).setPath(request.getRequestURI());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public R<?> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
		log.error("DataIntegrityViolationException:", ex);
		return R.result(ExceptionCode.SQL_EX).setPath(request.getRequestURI());
	}

}
