package cn.codingjc.peekaboo.domain.exception;

public enum ErrorCodeEnum {
    TOKEN_INVALID(100001, "token失效,请重新登录"),
    NOT_AUTHTICATION(100002, "尚未登录,请登录");

    private Integer code;
    private String message;

    ErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
