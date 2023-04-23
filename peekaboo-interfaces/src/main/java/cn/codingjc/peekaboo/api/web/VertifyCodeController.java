package cn.codingjc.peekaboo.api.web;

import cn.codingjc.peekaboo.application.config.security.VerityCodeConfig;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @date: 2023/4/23 21:13
 * @auther: codingjc
 */

@Controller
public class VertifyCodeController {

   @Autowired
   Producer producer;

   @GetMapping("/vertifyCode")
   public void getVertifyCode(HttpSession session, HttpServletResponse response) throws IOException {
      response.setContentType("image/jpeg");
      String text = producer.createText();
//      session.setAttribute("vertify_code", text);
      VerityCodeConfig.code = text;
      BufferedImage image = producer.createImage(text);
      ServletOutputStream outputStream = null;
      try(ServletOutputStream out = response.getOutputStream()) {
         ImageIO.write(image, "jpg", out);
      }
   }
}
