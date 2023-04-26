package cn.codingjc.peekaboo.infrastructure.message.sms.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class SmsParamDTO {

    /**
     * 需要发送的手机号
     */
    private Set<String> phones;

    /**
     * 发送文案
     */
    private String content;
}
