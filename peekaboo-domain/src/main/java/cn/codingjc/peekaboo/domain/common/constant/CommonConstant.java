package cn.codingjc.peekaboo.domain.common.constant;

/**
 * @date: 2023/4/25 21:30
 * @auther: codingjc
 */
public interface CommonConstant {


    // ----------------------------------redis--------------------------------------
    public static final String REDIS_VERTIFY_CODE_KEY = "vertify_code:key:";
    public static final String REDIS_SMS_CODE_KEY = "sms_code:key:";
    public static final String REDIS_USER_TOKEN_KEY = "user_token:key:";

    // ----------------------------------auth--------------------------------------
    public static final String TOKEN_HEADER = "Authtication";
    public static final String TOKEN_HEAD = "Bear ";
    public static final String USERNAME = "username";
}
