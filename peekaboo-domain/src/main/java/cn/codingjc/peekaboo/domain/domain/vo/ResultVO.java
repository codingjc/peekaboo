package cn.codingjc.peekaboo.domain.domain.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultVO<T> {

    private Integer code;

    private String message;

    private T data;

    public ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultVO ok(String message){
        return ok(200, message, null);
    }

    public static <T> ResultVO ok(String message, T data){
        return ok(200, message, data);
    }

    public static <T> ResultVO ok(Integer code, String message, T data){
        return new ResultVO(code, message, data);
    }

    public static ResultVO fail(String message){
        return new ResultVO(500, message, null);
    }
}
