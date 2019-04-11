package com.coupon.api.enums;

public enum ErrorCodeEnum {

	PARAM("param.error"),
	TOKEN_ERROR("token.error"),
	VERIFY_CODE_ERROR("verify.code.error"),
	CAPTCHA_ERROR("captcha.error"),
	LOGIN_ERROR("login.error"),
	EMAIL_NOT_VERIFICATION_ERROR("email.not.verification.error"),
	EMAIL_DUPLICATE_ERROR("email.duplicate.error"),
	MOBILE_DUPLICATE_ERROR("mobile.duplicate.error"),
	MOBILE_NOT_REGISTER_ERROR("mobile.not.register.error"),
	SYSTEM("system.error"),
	UPLOAD_FILE_EMPTY_ERROR("upload.file.empty.error"),
	UPLOAD_FILE_TYPE_ERROR("upload.file.type.error"),
	PASSWORD_NOT_CONFIRM_ERROR("password.not.confirm.error");

	private String code;

	ErrorCodeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
