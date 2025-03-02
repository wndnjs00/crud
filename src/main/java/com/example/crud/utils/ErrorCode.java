package com.example.crud.utils;


import jakarta.annotation.PostConstruct;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * ******************************* Global Error CodeList ***************************************
 * HTTP Status Code
 * 400 : Bad Request
 * 401 : Unauthorized
 * 403 : Forbidden
 * 404 : Not Found
 * 500 : Internal Server Error
 * *********************************************************************************************
 */

public enum ErrorCode {
    SELECT_ERROR(500, "E001", "error.select"),
    INSERT_ERROR(500, "E002", "error.insert"),
    UPDATE_ERROR(500, "E003", "error.update"),
    DELETE_ERROR(500, "E004", "error.delete"),
    LOGIN_ERROR(401, "E005", "error.login"),
    SIGNUP_ERROR(400, "E006", "error.signup"),
    REFRESH_TOKEN_EXPIRED(401, "E007", "error.refreshTokenExpired"),
    BAD_REQUEST_ERROR(400, "E008", "error.badRequest"),
    EXIST_EMAIL(400, "E009", "error.existEmail"),
    INVALID_PASSWORD_ERROR(401, "E010", "error.invalidPassword"),
    NO_SUCH_USER_ERROR(404, "E011", "error.noSuchUser"),
    AUTHENTICATION_ERROR(401, "E012", "error.authenticationFailed"),
    INTERNAL_SERVER_ERROR(500, "E013", "error.internalServer");

    private final int status;
    private final String divisionCode;
    private final String messageKey;
    private static MessageSource messageSource;

    ErrorCode(int status, String divisionCode, String messageKey) {
        this.status = status;
        this.divisionCode = divisionCode;
        this.messageKey = messageKey;
    }

    @PostConstruct
    public static void setMessageSource(MessageSource source) {
        ErrorCode.messageSource = source;
    }

    public String getMessage() {
        return messageSource.getMessage(this.messageKey, (Object[])null, LocaleContextHolder.getLocale());
    }

    public int getStatus() {
        return this.status;
    }

    public String getDivisionCode() {
        return this.divisionCode;
    }

    public String getMessageKey() {
        return this.messageKey;
    }
}


