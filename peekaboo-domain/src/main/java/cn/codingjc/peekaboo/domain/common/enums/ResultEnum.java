package cn.codingjc.peekaboo.domain.common.enums;

public enum ResultEnum {
    PERMISSION_DENIED("未授权,无法访问"),
    AUTHTICATION_FAIL("认证失败,请重新登录");

    private String status;

    ResultEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
