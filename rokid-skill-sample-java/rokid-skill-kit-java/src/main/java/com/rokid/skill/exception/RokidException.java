package com.rokid.skill.exception;

import org.springframework.http.HttpStatus;

/**
 * 通用异常情况
 * 
 * @author Bassam
 *
 */
public class RokidException extends Exception {

	private static final long serialVersionUID = 9135801019232303753L;
	// 默认提供了一定的错误定义///
	public static final String ERROR_CODE_SUCCESS = HttpStatus.OK.name();// 成功
	public static final String ERROR_CODE_NOTFOUND = "NotFound";// 没有找到
	public static final String ERROR_CODE_DATABASE = "DatabaseError";// 数据库错误
	public static final String ERROR_CODE_REDIS = "RedisError";// Redis错误
	public static final String ERROR_CODE_REDISFOUND = "RedisNotFound";// Redis数据没有找到
	public static final String ERROR_CODE_SECURITY = "SecurityError";// 安全检查错误
	public static final String ERROR_CODE_INVALIDHEAD = "InvalidHead";// 请求头有误
	public static final String ERROR_CODE_INVALIDBODY = "InvalidBody";// 请求数据有误
	public static final String ERROR_CODE_INVALIDARGUMENT = "InvalidArgument";// 无效的参数
	public static final String ERROR_CODE_RESONSE = "ResponseError";// 响应错误
	public static final String ERROR_CODE_MISSINGARGUMENT = "MissingArgument";// 参数缺失
	public static final String ERROR_CODE_METHODNOTALLOWED = "MethodNotAllowed";// 方法不支持
	public static final String ERROR_CODE_INTERNAL = "InternalError";// 内部错误
	public static final String ERROR_CODE_ACCESS = "AccessDenied";// 接入出错

	private int httpStatusCode;// Http状态码
	private String errorCode;// 操作码

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public RokidException(int httpStatusCode, String errorCode) {
		super();
		this.httpStatusCode = httpStatusCode;
		this.errorCode = errorCode;
	}

	public RokidException(int httpStatusCode, String errorCode, String message) {
		super(message);
		this.httpStatusCode = httpStatusCode;
		this.errorCode = errorCode;
	}

	public RokidException(int httpStatusCode, String errorCode, String message, Throwable throwable) {
		super(message, throwable);
		this.httpStatusCode = httpStatusCode;
		this.errorCode = errorCode;
	}

	public RokidException(int httpStatusCode, String errorCode, Throwable throwable) {
		super(throwable);
		this.httpStatusCode = httpStatusCode;
		this.errorCode = errorCode;
	}
}
