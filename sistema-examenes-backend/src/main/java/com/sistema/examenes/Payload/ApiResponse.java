/**
 * Clase genérica para devolver respuestas json en las API's.....
 */

package com.sistema.examenes.Payload;

public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;
    private String detail;

    public ApiResponse() {}

    public ApiResponse(String status, String message, T data, String detail) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.detail = detail;
    }

    // Getters y Setters
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
}
