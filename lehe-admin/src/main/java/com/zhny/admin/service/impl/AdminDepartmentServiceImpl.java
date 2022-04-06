package com.zhny.admin.service.impl;

import com.zhny.admin.entity.AdminDepartment;
import com.zhny.admin.mapper.IAdminDepartmentMapper;
import com.zhny.admin.service.IAdminDepartmentService;
import com.lehe.starter.mybatisplus.base.BaseMpServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统部门表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2021-09-22
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class AdminDepartmentServiceImpl extends BaseMpServiceImpl<IAdminDepartmentMapper, AdminDepartment> implements IAdminDepartmentService {

    private IAdminDepartmentMapper AdminDepartmentMapper;

}
