package com.example.crud.utils;


// API Response 결과의 반환 값을 관리
public class ApiResponse<T> {

    private int status;         // API 응답 상태
    private String resultMsg;   // API 응답 메시지
    private String divisionCode;  // API 구분값 반환
    private T data;

    // 성공 응답
    public static <T> ApiResponse<T> success(SuccessCode successCode, T data) {
        return new ApiResponse<T>(successCode.getStatus(), successCode.getMessage(), (String)null, data);
    }

    // 성공 응답
    public static <T> ApiResponse<T> success(SuccessCode successCode) {
        return new ApiResponse<T>(successCode.getStatus(), successCode.getMessage(), (String)null, (T) null);
    }

    // 에러 응답
    public static <T> ApiResponse<T> error(ErrorCode errorCode, T data) {
        return new ApiResponse<T>(errorCode.getStatus(), errorCode.getMessage(), errorCode.getDivisionCode(), data);
    }

    // 기본 생성자
    public ApiResponse() {
    }

    // 모든 필드를 초기화하는 생성자
    public ApiResponse(int status, String resultMsg, String divisionCode, T data) {
        this.status = status;
        this.resultMsg = resultMsg;
        this.divisionCode = divisionCode;
        this.data = data;
    }


    // Getter, Setter
    public int getStatus() {
        return status;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public T getData() {
        return data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public void setData(T data) {
        this.data = data;
    }

}

