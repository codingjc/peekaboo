package cn.codingjc.peekaboo.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @auth: coding_jc
 * @date: 2023/4/12 22:21
 * @desc: base基础类
 */

@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 搜索值
     */
    @TableField(exist = false)
    private String searchValue;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 请求参数
     */
    @TableField(exist = false)
    private Map<String, Object> params;

    public void initForInsert(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUserPO sysUserPO = (SysUserPO) authentication.getPrincipal();
        this.createTime = new Date();
        this.updateTime = new Date();
        this.createBy = sysUserPO.getUserId();
        this.updateBy = sysUserPO.getUserId();
    }
}
