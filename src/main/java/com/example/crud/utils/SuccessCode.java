package com.example.crud.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public enum SuccessCode {
    SELECT_SUCCESS(200, "G01", "success.select"),
    DELETE_SUCCESS(200, "G02", "success.delete"),
    INSERT_SUCCESS(201, "G03", "success.insert"),
    UPDATE_SUCCESS(200, "G04", "success.update"),
    ALL_DATA_FIND_SUCCESS(200, "G05", "success.all.find.data"),
    ALL_ID_FIND_SUCCESS(200, "G05", "success.find.id"),
    REFRESH_TOKEN_SUCCESS(200, "G06", "success.refreshToken"),
    LOGIN_SUCCESS(200, "G07", "success.login"),
    SIGNUP_SUCCESS(201, "G08", "success.signup"),
    LOGOUT_SUCCESS(200, "G09", "success.logout");


    private final int status;   // 상태 반환
    private final String divisionCode;  // 성공코드의 구분값 반환
    private final String messageKey;    // 메시지값을 반환
    private static MessageSource messageSource;

    // 생성자 구성
    SuccessCode(int status, String code, String messageKey) {
        this.status = status;
        this.divisionCode = code;
        this.messageKey = messageKey;
    }

    @PostConstruct
    public static void setMessageSource(MessageSource source) {
        SuccessCode.messageSource = source;
    }

    public String getMessage() {
        return messageSource.getMessage(this.messageKey, null, LocaleContextHolder.getLocale());
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


