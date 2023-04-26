package cn.codingjc.peekaboo.application.util;

import cn.codingjc.peekaboo.infrastructure.util.SpringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageUtils {

    public static String getMessage(String code, Object... args){
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
