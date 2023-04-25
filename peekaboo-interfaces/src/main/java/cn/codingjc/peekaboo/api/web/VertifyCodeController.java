package cn.codingjc.peekaboo.api.web;

import cn.codingjc.peekaboo.application.config.security.VerityCodeConfig;
import cn.codingjc.peekaboo.application.util.MessageUtils;
import cn.codingjc.peekaboo.domain.domain.vo.ResultVO;
import cn.codingjc.peekaboo.domain.domain.vo.VertifyCodeVO;
import cn.hutool.core.lang.UUID;
import com.google.code.kaptcha.Producer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @date: 2023/4/23 21:13
 * @auther: codingjc
 */

@Slf4j
@Controller
@AllArgsConstructor
public class VertifyCodeController {

   private final Producer producer;

   @GetMapping("/vertifyCode")
   public ResultVO getVertifyCode(HttpServletResponse response) throws IOException {
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setCharacterEncoding(StandardCharsets.UTF_8.name());
      String text = producer.createText();
      VerityCodeConfig.code = text;
      BufferedImage image = producer.createImage(text);
      try{
         ServletOutputStream out = response.getOutputStream();
         ImageIO.write(image, "jpg", out);
         byte[] b = new byte[]{};
         out.write(b);
         String imageStr = Base64.getEncoder().encodeToString(b);
         String uuid = UUID.fastUUID().toString();
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
