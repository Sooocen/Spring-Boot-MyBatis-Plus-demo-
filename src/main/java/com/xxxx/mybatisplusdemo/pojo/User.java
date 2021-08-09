package com.xxxx.mybatisplusdemo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Sooocen
 * @since 2021-08-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */ //主键策略
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date UpdateTime;

    /**
     * 版本
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    /**
     * 账户是否可用
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
