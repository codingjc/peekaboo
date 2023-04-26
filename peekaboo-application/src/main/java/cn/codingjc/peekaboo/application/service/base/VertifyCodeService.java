package cn.codingjc.peekaboo.application.service.base;

import cn.codingjc.peekaboo.domain.domain.vo.VertifyCodeVO;
import cn.codingjc.peekaboo.infrastructure.message.sms.pojo.SmsParamDTO;

import javax.servlet.http.HttpServletResponse;

public interface VertifyCodeService {
    VertifyCodeVO getVertifyCode(HttpServletResponse response);

    void sendSms(SmsParamDTO smsParamDTO);
}
