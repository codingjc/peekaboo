package cn.codingjc.peekaboo.api.config;

import cn.codingjc.peekaboo.domain.domain.vo.ResultVO;
import cn.codingjc.peekaboo.domain.exception.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobelExceptionHandle {

    @ExceptionHandler(value = BusinessException.class)
    public ResultVO bussionessException(BusinessException exception){
        return ResultVO.fail(exception.getMessage());
    }
}
