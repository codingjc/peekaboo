package cn.codingjc.peekaboo.domain.domain.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VertifyCodeVO {

    private String uuid;
    private String imageStr;

}
