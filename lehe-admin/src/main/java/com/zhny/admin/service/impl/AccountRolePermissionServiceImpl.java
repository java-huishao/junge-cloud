package com.zhny.admin.service.impl;

import com.zhny.admin.entity.AccountRolePermission;
import com.zhny.admin.mapper.IAccountRolePermissionMapper;
import com.zhny.admin.service.IAccountRolePermissionService;
import com.lehe.starter.mybatisplus.base.BaseMpServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2021-09-16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class AccountRolePermissionServiceImpl extends BaseMpServiceImpl<IAccountRolePermissionMapper, AccountRolePermission> implements IAccountRolePermissionService {

    private IAccountRolePermissionMapper AccountRolePermissionMapper;

}
