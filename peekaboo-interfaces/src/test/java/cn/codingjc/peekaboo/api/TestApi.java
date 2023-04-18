package cn.codingjc.peekaboo.api;


import cn.codingjc.peekaboo.infrastructure.persistence.mapper.SysUserMapper;
import cn.codingjc.peekaboo.infrastructure.persistence.po.SysUserPO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestApi {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void test1(){
        QueryWrapper<SysUserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", 1);
        SysUserPO sysUserPO = sysUserMapper.selectOne(queryWrapper);
        System.out.println(sysUserPO);

    }
}
