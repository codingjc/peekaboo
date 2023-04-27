package cn.codingjc.peekaboo.domain.repository;

import cn.codingjc.peekaboo.domain.domain.dto.RegisterRequestDTO;
import cn.codingjc.peekaboo.infrastructure.persistence.mapper.SysUserMapper;
import cn.codingjc.peekaboo.infrastructure.persistence.po.SysUserPO;
import cn.codingjc.peekaboo.infrastructure.util.RedisUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static cn.codingjc.peekaboo.domain.common.constant.CommonConstant.REDIS_USER_TOKEN_KEY;

@Component
public class UserRepository {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Value("${user.token.timeout}")
    private Integer userTokeneTimeout;

    public SysUserPO getUserByUsername(String username){
        QueryWrapper<SysUserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        return sysUserMapper.selectOne(queryWrapper);
    }

    public SysUserPO getUserByPhonenumber(String phonenumber){
        QueryWrapper<SysUserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phonenumber", phonenumber);
        return sysUserMapper.selectOne(queryWrapper);
    }

    public void saveToken(String username, String token) {
        RedisUtils.set(REDIS_USER_TOKEN_KEY + username, token, userTokeneTimeout);
    }

    public void deleteToken(String username) {
        RedisUtils.del(REDIS_USER_TOKEN_KEY + username);
    }

    public String getTokenByUserName(String username){
        return (String) RedisUtils.get(REDIS_USER_TOKEN_KEY + username);
    }

    public void reflush(String username, String token){
        deleteToken(username);
        saveToken(username, token);
    }

    public boolean saveRegisterUser(RegisterRequestDTO registerRequestDTO) {
        SysUserPO sysUserPO = new SysUserPO();
        sysUserPO.initForInsert();
        BeanUtils.copyProperties(registerRequestDTO, sysUserPO);
        sysUserPO.setUserType(1);
        sysUserPO.setStatus("0");
        sysUserPO.setDelFlag("0");
        return sysUserMapper.insert(sysUserPO) == 1;
    }

    public SysUserPO savePhoneUser(String phonenumber) {
        SysUserPO sysUserPO = new SysUserPO();
        sysUserPO.initForInsert();
        sysUserPO.setPhonenumber(phonenumber);
        sysUserPO.setUserName(phonenumber);
        sysUserPO.setNickName(phonenumber);
        // todo 手机默认头像
        sysUserPO.setAvatar(null);
        sysUserPO.setUserType(2);
        sysUserPO.setStatus("0");
        sysUserPO.setDelFlag("0");
        return sysUserPO;
    }
}
