package cn.codingjc.peekaboo.application.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @date: 2023/4/24 22:02
 * @auther: codingjc
 */
public class SpringUtils implements ApplicationContextAware {

   private static ApplicationContext applicationContext;

   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      applicationContext = applicationContext;
   }

   public static <T> T getBean(Class<T> clazz){
      return applicationContext.getBean(clazz);
   }

}
