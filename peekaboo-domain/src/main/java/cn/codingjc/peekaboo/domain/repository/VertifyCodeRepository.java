package cn.codingjc.peekaboo.domain.repository;

import cn.codingjc.peekaboo.infrastructure.util.RedisUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static cn.codingjc.peekaboo.domain.common.constant.CommonConstant.*;

@Component
public class VertifyCodeRepository {

    @Value("${vertify.code.timeout}")
    private Integer vertifyCodeTimeout;

    @Value("${sms.code.timeout}")
    private Integer smsCodeTimeout;

    public void saveVertifyCode (String uuid, String code) {
        RedisUtils.set(REDIS_VERTIFY_CODE_KEY + uuid, code, vertifyCodeTimeout);
    }

    public void saveSmsCode(String phone, String smsCode){
        RedisUtils.set(REDIS_SMS_CODE_KEY + phone, smsCode, smsCodeTimeout);
    }

}
