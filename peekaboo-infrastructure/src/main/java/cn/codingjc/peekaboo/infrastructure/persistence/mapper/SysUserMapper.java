package cn.codingjc.peekaboo.infrastructure.persistence.mapper;

import cn.codingjc.peekaboo.infrastructure.persistence.po.SysUserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @auth: coding_jc
 * @date: 2023/4/13 00:04
 * @desc:
 */

@Mapper
public interface SysUserMapper extends BaseMapper<SysUserPO> {
}
