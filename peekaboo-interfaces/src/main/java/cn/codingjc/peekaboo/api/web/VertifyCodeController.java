package cn.codingjc.peekaboo.api.web;

import cn.codingjc.peekaboo.application.config.security.VerityCodeConfig;
import cn.codingjc.peekaboo.application.util.MessageUtils;
import cn.codingjc.peekaboo.application.util.RedisUtils;
import cn.codingjc.peekaboo.domain.domain.vo.ResultVO;
import cn.codingjc.peekaboo.domain.domain.vo.VertifyCodeVO;
import cn.hutool.core.lang.UUID;
import com.google.code.kaptcha.Producer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static cn.codingjc.peekaboo.domain.common.constant.CommonConstant.REDIS_VERTIFY_CODE_KEY;

/**
 * @date: 2023/4/23 21:13
 * @auther: codingjc
 */

@Slf4j
@RestController
public class VertifyCodeController {

   @Autowired
   private Producer producer;

   @Value("${vertify.code.timeout}")
   private Integer vertifyCodeTimeout;

   @GetMapping("/vertifyCode")
   public ResultVO getVertifyCode(HttpServletResponse response) throws IOException {
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setCharacterEncoding(StandardCharsets.UTF_8.name());
      String text = producer.createText();
      VerityCodeConfig.code = text;
      BufferedImage image = producer.createImage(text);

      // 存入redis
      try{
         FastByteArrayOutputStream os = new FastByteArrayOutputStream();
         ImageIO.write(image, "jpg", os);
         byte[] b = new byte[]{};
         String imageStr = Base64.getEncoder().encodeToString(os.toByteArray());
         String uuid = UUID.fastUUID().toString();
         RedisUtils.set(REDIS_VERTIFY_CODE_KEY + uuid, text, vertifyCodeTimeout);
         VertifyCodeVO vertifyCodeVO = VertifyCodeVO.builder()
                 .uuid(uuid)
                 .image(imageStr)
                 .build();
         return ResultVO.ok("success", vertifyCodeVO);
      } catch (Exception e){
         log.error("[VertifyCodeController][getVertifyCode] conversion error:", e);
      }
      return ResultVO.fail(MessageUtils.getMessage("get.vertifyCode.fail"));
   }
}
