package cn.codingjc.peekaboo.application.service.login;

import cn.codingjc.peekaboo.application.util.MessageUtils;
import cn.codingjc.peekaboo.domain.domain.dto.LoginRequestDTO;
import cn.codingjc.peekaboo.domain.exception.BusinessException;
import cn.codingjc.peekaboo.domain.repository.UserRepository;
import cn.codingjc.peekaboo.infrastructure.persistence.po.SysUserPO;
import cn.codingjc.peekaboo.infrastructure.util.RedisUtils;
import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static cn.codingjc.peekaboo.domain.common.constant.CommonConstant.REDIS_SMS_CODE_KEY;

@Slf4j
@Service
@AllArgsConstructor
public class SmsUserLogin extends UserLogin{

    private final UserRepository userRepository;

    @Override
    public SysUserPO doLogin(LoginRequestDTO loginRequestDTO) {
        String phonenumber = loginRequestDTO.getPhonenumber();
        String smsCode = loginRequestDTO.getSmsCode();
        String redisSmsCode = (String) RedisUtils.get(REDIS_SMS_CODE_KEY + phonenumber);
        if (ObjectUtil.isEmpty(redisSmsCode)) {
            throw new BusinessException(MessageUtils.getMessage("vertify.code.expired"));
        }
        if (!smsCode.equalsIgnoreCase(redisSmsCode)) {
            throw new BusinessException(MessageUtils.getMessage("vertify.code.error"));
        }
        SysUserPO sysUserPO = userRepository.getUserByPhonenumber(phonenumber);
        if (ObjectUtil.isEmpty(sysUserPO)) {
            // todo 为注册过的用户给以注册
        } else {
            return sysUserPO;
        }
        return null;
    }
}
