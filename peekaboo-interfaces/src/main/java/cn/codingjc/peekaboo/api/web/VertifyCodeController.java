package cn.codingjc.peekaboo.api.web;

import cn.codingjc.peekaboo.application.service.base.VertifyCodeService;
import cn.codingjc.peekaboo.application.util.MessageUtils;
import cn.codingjc.peekaboo.domain.domain.vo.ResultVO;
import cn.codingjc.peekaboo.domain.domain.vo.VertifyCodeVO;
import cn.codingjc.peekaboo.infrastructure.message.sms.pojo.SmsParamDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @date: 2023/4/23 21:13
 * @auther: codingjc
 */

@Slf4j
@RestController
public class VertifyCodeController {

   @Autowired
   private VertifyCodeService vertifyCodeService;

   /**
    * 获取验证码
    * @param response
    * @return
    * @throws IOException
    */
   @GetMapping("/vertifyCode")
   public ResultVO getVertifyCode(HttpServletResponse response) throws IOException {
      VertifyCodeVO vertifyCodeVO = vertifyCodeService.getVertifyCode(response);
      return ResultVO.ok("success", vertifyCodeVO);
   }

   /**
    * 发送短信
    * @param smsParamDTO
    * @return
    */
   @PostMapping("/sendSms")
   public ResultVO sendSms(@RequestBody SmsParamDTO smsParamDTO){
      vertifyCodeService.sendSms(smsParamDTO);
      return ResultVO.ok(MessageUtils.getMessage("sms.send.success"));
   }
}
