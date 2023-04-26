package cn.codingjc.peekaboo.application.config.security;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @date:   2023/4/23 20:03
 * @auther: codingjc
 */

@Configuration
public class VerityCodeConfig {

   @Bean
   Producer veritfyCode(){
      Properties properties = new Properties();
      properties.setProperty("kaptcha.image.width", "150");
      properties.setProperty("kaptcha.image.height", "50");
      properties.setProperty("kaptcha.textproducer.char.string", "0123456789");
      properties.setProperty("kaptcha.textproducer.char.length", "4");
      Config config = new Config(properties);
      DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
      defaultKaptcha.setConfig(config);
      return defaultKaptcha;
   }

}
