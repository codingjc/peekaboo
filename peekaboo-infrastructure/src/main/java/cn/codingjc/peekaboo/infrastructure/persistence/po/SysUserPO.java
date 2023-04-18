package cn.codingjc.peekaboo.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @auth: coding_jc
 * @date: 2023/4/12 22:27
 * @desc: 用户持久类
 */

@Data
@TableName("sys_user")
public class SysUserPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户类型（00系统用户）
     */
    private Integer userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 联系方式
     */
    private String phonenumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private Date loginDate;

    @TableField(exist = false)
    private List<SysRolePO> roles;
}
