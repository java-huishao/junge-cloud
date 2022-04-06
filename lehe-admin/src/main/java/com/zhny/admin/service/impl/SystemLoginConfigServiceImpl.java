package com.zhny.admin.service.impl;

import com.zhny.admin.entity.SystemLoginConfig;
import com.zhny.admin.mapper.ISystemLoginConfigMapper;
import com.zhny.admin.service.ISystemLoginConfigService;
import com.lehe.starter.mybatisplus.base.BaseMpServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 登录背景配置表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2021-09-22
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class SystemLoginConfigServiceImpl extends BaseMpServiceImpl<ISystemLoginConfigMapper, SystemLoginConfig> implements ISystemLoginConfigService {

    private ISystemLoginConfigMapper SystemLoginConfigMapper;

}
