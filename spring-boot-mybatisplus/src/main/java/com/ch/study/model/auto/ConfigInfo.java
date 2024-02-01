package com.ch.study.model.auto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * config_info
 * </p>
 *
 * @author astupidcoder
 * @since 2024-01-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ConfigInfo extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * data_id
     */
    private String dataId;

    /**
     * group_id
     */
    private String groupId;

    /**
     * content
     */
    private String content;

    /**
     * md5
     */
    private String md5;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * source user
     */
    private String srcUser;

    /**
     * source ip
     */
    private String srcIp;

    /**
     * app_name
     */
    private String appName;

    /**
     * 租户字段
     */
    private String tenantId;

    /**
     * configuration description
     */
    private String cDesc;

    /**
     * configuration usage
     */
    private String cUse;

    /**
     * 配置生效的描述
     */
    private String effect;

    /**
     * 配置的类型
     */
    private String type;

    /**
     * 配置的模式
     */
    private String cSchema;

    /**
     * 密钥
     */
    private String encryptedDataKey;


}
