package com.ch.study.service;

import com.ch.study.model.auto.ConfigInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * config_info 服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2024-01-31
 */
public interface IConfigInfoService extends IService<ConfigInfo> {
    List<ConfigInfo> findAllConfig();

}
