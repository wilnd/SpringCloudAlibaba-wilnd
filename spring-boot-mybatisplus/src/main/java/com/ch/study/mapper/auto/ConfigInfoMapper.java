package com.ch.study.mapper.auto;

import com.ch.study.model.auto.ConfigInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * config_info Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2024-01-31
 */
public interface ConfigInfoMapper extends BaseMapper<ConfigInfo> {

    List<ConfigInfo> findAllConfig();

}
