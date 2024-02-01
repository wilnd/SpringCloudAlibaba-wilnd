package com.ch.study.service.impl;

import com.ch.study.model.auto.ConfigInfo;
import com.ch.study.mapper.auto.ConfigInfoMapper;
import com.ch.study.service.IConfigInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * config_info 服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2024-01-31
 */
@Service
public class ConfigInfoServiceImpl extends ServiceImpl<ConfigInfoMapper, ConfigInfo> implements IConfigInfoService {

    @Autowired
    private ConfigInfoMapper configInfoMapper;

    @Override
    public List<ConfigInfo> findAllConfig() {
        return configInfoMapper.findAllConfig();
    }
}
