package com.zhny.admin.service.impl;

import com.zhny.admin.entity.AccountRole;
import com.zhny.admin.mapper.IAccountRoleMapper;
import com.zhny.admin.service.IAccountRoleService;
import com.lehe.starter.mybatisplus.base.BaseMpServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 后台账号角色中间表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2021-09-16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class AccountRoleServiceImpl extends BaseMpServiceImpl<IAccountRoleMapper, AccountRole> implements IAccountRoleService {

    private IAccountRoleMapper AccountRoleMapper;
}
