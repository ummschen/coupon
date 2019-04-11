package com.coupon.api.exception;

import com.coupon.api.enums.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceException extends RuntimeException {
	private ErrorCodeEnum errorCode;
	private String errorMsg;

	public ServiceException(ErrorCodeEnum errorCode) {
		this.errorCode = errorCode;
	}

	public ServiceException(ErrorCodeEnum errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public ServiceException(Throwable cause) {
		super(cause);
		this.errorCode = ErrorCodeEnum.SYSTEM;
		this.errorMsg = cause.getMessage();
	}

	@Override
	public String getMessage() {
		return this.getErrorMsg();
	}
}
