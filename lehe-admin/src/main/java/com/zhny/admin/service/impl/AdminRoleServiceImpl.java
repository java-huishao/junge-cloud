package com.zhny.admin.service.impl;

import com.zhny.admin.entity.AdminRole;
import com.zhny.admin.mapper.IAdminRoleMapper;
import com.zhny.admin.service.IAdminRoleService;
import com.lehe.starter.mybatisplus.base.BaseMpServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 后台角色表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2021-09-16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class AdminRoleServiceImpl extends BaseMpServiceImpl<IAdminRoleMapper, AdminRole> implements IAdminRoleService {

    private IAdminRoleMapper AdminRoleMapper;

}
