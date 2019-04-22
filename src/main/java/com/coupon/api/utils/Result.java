package com.coupon.api.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * 功能描述: 这个类主要是用于规范REST接口的默认返回结构
 * 			{"code":"", "msg":"", "data":{Object}, "success":true}
 *          针对一些REST接口的默认异常处理，可能会使用这个类作为默认返回
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("unused")
public class Result<T> implements Serializable {

	private static final long serialVersionUID = -1872625368730485194L;

	public static final int CODE_SUCCESS = 200;                //成功
	public static final int CODE_SYS_ERR = 500;                // 系统错误
	public static final int CODE_PARAMS_ERR = 400;            // 参数错误
	public static final int CODE_ACCESS_TOkEN_ERR = 401;       // token值错误
	public static final int CODE_AUTHORITY_ERR = 403;        // 权限错误
	public static final int CODE_NOT_FOUND = 404;            // 非法操作
	public static final int CODE_ENCRYPTION_ERR = 405;    // 加密错误
	public static final int CODE_DO_FAIL = 999;
	public static final String SYS_ERR_STRING = "系统错误";

	public Result<T> code(int code) {
		this.code = code;
		return this;
	}

	public Result<T> message(String message) {
		this.message = message;
		return this;
	}

	public Result<T> data(T result) {
		this.data = result;
		return this;
	}

	public Result() {
	}

	public static <T> Result<T> ofSuccess(T value) {
		Result<T> rslt = new Result<T>();
		rslt.setCode(CODE_SUCCESS);
		rslt.setData(value);
		return rslt;
	}

	public static <T> Result<T> ofError(String message) {
		Result<T> rslt = new Result<T>();
		rslt.setCode(CODE_DO_FAIL);
		rslt.setMessage(message);
		return rslt;
	}

	public static <T> Result<T> ofSuccess(String message) {
		Result<T> rslt = new Result<T>();
		rslt.setCode(CODE_SUCCESS);
		rslt.setMessage(message);
		return rslt;
	}

	public static <T> Result<T> ofParamsError(String message) {
		Result<T> rslt = new Result<T>();
		rslt.setCode(CODE_PARAMS_ERR);
		rslt.setMessage(message);
		return rslt;
	}

	public static <T> Result<T> ofSystemError(String message) {
		Result<T> rslt = new Result<T>();
		rslt.setCode(CODE_SYS_ERR);
		rslt.setMessage(message);
		return rslt;
	}

	public static <T> Result<T> ofTokenError(String message) {
		Result<T> rslt = new Result<T>();
		rslt.setCode(CODE_ACCESS_TOkEN_ERR);
		rslt.setMessage(message);
		return rslt;
	}


	public static <T> Result<T> ofSuccess(String message, T value) {
		Result<T> rslt = new Result<T>();
		rslt.setCode(CODE_SUCCESS);
		rslt.setData(value);
		rslt.setMessage(message);
		return rslt;
	}

	/**
	 * 错误码
	 **/
	public int code = CODE_SUCCESS;

	/**
	 * 返回信息
	 **/
	public String message;

	/**
	 * 返回对象 存放具体的json数据
	 **/
	public T data;

	@JsonProperty("success")
	public boolean getSuccess() {
		return this.code == CODE_SUCCESS;
	}

	/**
	 * 用于安全的拆包
	 * 当success 为true时可以获取正确的data对象[T]
	 * 当success 为false时
	 * 1.result.open(null)
	 * 则只返回null对象，不会抛出异常
	 * <p>
	 * 2.result.open(p -> {
	 * //log error etc.
	 * throw new BusinessException(result.getMessage(), this);
	 * })
	 * 抛出 BusinessException
	 *
	 * @param failedConsumer 异常时，消费方法
	 * @return
	 */
	@JsonIgnore
	public T open(Consumer<Result<T>> failedConsumer) {
		if (this.getSuccess()) {
			return this.getData();
		} else if (failedConsumer != null) {
			failedConsumer.accept(this);
		}
		return null;
	}

}