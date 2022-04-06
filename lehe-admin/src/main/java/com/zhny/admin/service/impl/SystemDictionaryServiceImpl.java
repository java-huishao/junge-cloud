package com.zhny.admin.service.impl;

import com.zhny.admin.entity.SystemDictionary;
import com.zhny.admin.mapper.ISystemDictionaryMapper;
import com.zhny.admin.service.ISystemDictionaryService;
import com.lehe.starter.mybatisplus.base.BaseMpServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统字典分类表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2021-09-22
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class SystemDictionaryServiceImpl extends BaseMpServiceImpl<ISystemDictionaryMapper, SystemDictionary> implements ISystemDictionaryService {

    private ISystemDictionaryMapper SystemDictionaryMapper;

}
