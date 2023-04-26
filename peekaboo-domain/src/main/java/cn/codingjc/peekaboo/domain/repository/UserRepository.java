package cn.codingjc.peekaboo.domain.repository;

import cn.codingjc.peekaboo.infrastructure.persistence.mapper.SysUserMapper;
import cn.codingjc.peekaboo.infrastructure.persistence.po.SysUserPO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    @Autowired
    private SysUserMapper sysUserMapper;

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
}
