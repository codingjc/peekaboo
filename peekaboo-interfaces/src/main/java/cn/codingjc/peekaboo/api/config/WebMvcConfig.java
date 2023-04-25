package cn.codingjc.peekaboo.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 国际化配置
     */
    @Bean
    public LocaleResolver localeResolver(){
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
		return sessionLocaleResolver;
        //使用CookieLocaleResolver,也可使用SessionLocaleResolver，区别在于一个使用Cookie存储Locale信息，一个使用Session
//        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
//        localeResolver.setCookieName("localeCookie");
//        //设置默认区域
//        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
//        localeResolver.setCookieMaxAge(3600);//设置cookie有效期.
//        return localeResolver;
    }


    /**
     *
     * 添加Locale 拦截器，从请求参数lang中获取参数值，这样我们可以通过lang RequestParam来切换Locale信息
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加locale拦截器
        registry.addInterceptor(localeChangeInterceptor());
    }


}
