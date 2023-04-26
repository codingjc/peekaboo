package cn.codingjc.peekaboo.application.service.base.impl;

import cn.codingjc.peekaboo.application.service.base.VertifyCodeService;
import cn.codingjc.peekaboo.application.util.MessageUtils;
import cn.codingjc.peekaboo.domain.domain.vo.VertifyCodeVO;
import cn.codingjc.peekaboo.domain.exception.BusinessException;
import cn.codingjc.peekaboo.domain.repository.VertifyCodeRepository;
import cn.codingjc.peekaboo.infrastructure.message.sms.TencentSmsHandle;
import cn.codingjc.peekaboo.infrastructure.message.sms.pojo.SmsParamDTO;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.NumberUtil;
import com.google.code.kaptcha.Producer;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Service
public class VertifyCodeServiceImpl implements VertifyCodeService {

    @Autowired
    private Producer producer;

    @Autowired
    private TencentSmsHandle tencentSmsHandle;

    @Autowired
    private VertifyCodeRepository vertifyCodeRepository;

    @Override
    public VertifyCodeVO getVertifyCode(HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);

        try{
            FastByteArrayOutputStream os = new FastByteArrayOutputStream();
            ImageIO.write(image, "jpg", os);
            String imageStr = Base64.getEncoder().encodeToString(os.toByteArray());
            String uuid = UUID.fastUUID().toString();
            // 存入redis
            vertifyCodeRepository.saveVertifyCode(uuid, text);
            VertifyCodeVO vertifyCodeVO = VertifyCodeVO.builder()
                    .uuid(uuid)
                    .imageStr(imageStr)
                    .build();
            return vertifyCodeVO;
        } catch (Exception e){
            log.error("[VertifyCodeController][getVertifyCode] conversion error:", e);
            throw new BusinessException(MessageUtils.getMessage("get.vertifyCode.fail"));
        }
    }

    @Override
    public void sendSms(SmsParamDTO smsParamDTO) {
        int[] randomNumArray = NumberUtil.generateRandomNumber(0, 9, 6);
        String randomText = StringUtils.join(randomNumArray);
        String messageTemplate = "【peekaboo】验证码为：%s，有效期为10分钟!";
        smsParamDTO.setContent(String.format(messageTemplate, randomText));
        try {
            String result = tencentSmsHandle.send(smsParamDTO);
            log.debug("[VertifyCodeServiceImpl][sendSms] sendSms result:{}", result);
            for (String phone : smsParamDTO.getPhones()) {
                // 存入redis
                vertifyCodeRepository.saveSmsCode(phone, randomText);
            }
        } catch (TencentCloudSDKException e) {
            log.error("[VertifyCodeServiceImpl][sendSms] send error:", e);
            throw new BusinessException(MessageUtils.getMessage("sms.send.fail"));
        }
    }
}
