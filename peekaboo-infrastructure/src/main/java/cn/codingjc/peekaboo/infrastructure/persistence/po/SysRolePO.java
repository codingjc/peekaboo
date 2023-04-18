package cn.codingjc.peekaboo.infrastructure.persistence.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class SysRolePO extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    @TableId(type = IdType.AUTO)
    private Long roleId;

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private String dataScope;

    private Integer menuCheckStrictly;

    private Integer deptCheckStrictly;

    private String status;

    private String delFlag;
}
