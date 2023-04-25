package cn.codingjc.peekaboo.domain.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
    }
}
