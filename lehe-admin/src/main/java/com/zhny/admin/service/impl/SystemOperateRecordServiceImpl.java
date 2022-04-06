package com.zhny.admin.service.impl;

import com.zhny.admin.entity.SystemOperateRecord;
import com.zhny.admin.mapper.ISystemOperateRecordMapper;
import com.zhny.admin.service.ISystemOperateRecordService;
import com.lehe.starter.mybatisplus.base.BaseMpServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2021-09-22
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class SystemOperateRecordServiceImpl extends BaseMpServiceImpl<ISystemOperateRecordMapper, SystemOperateRecord> implements ISystemOperateRecordService {

    private ISystemOperateRecordMapper SystemOperateRecordMapper;

}
