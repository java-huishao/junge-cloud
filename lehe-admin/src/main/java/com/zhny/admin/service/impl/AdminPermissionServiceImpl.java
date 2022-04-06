package com.zhny.admin.service.impl;

import com.zhny.admin.entity.AdminPermission;
import com.zhny.admin.mapper.IAdminPermissionMapper;
import com.zhny.admin.service.IAdminPermissionService;
import com.lehe.starter.mybatisplus.base.BaseMpServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 后台权限表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2021-09-16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class AdminPermissionServiceImpl extends BaseMpServiceImpl<IAdminPermissionMapper, AdminPermission> implements IAdminPermissionService {

    private IAdminPermissionMapper AdminPermissionMapper;

    /**
     * 根据用户id查询所有可按钮访问权限列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> getPermissionByUserId(String userId) {
        return AdminPermissionMapper.getPermissionByUserId(userId);
    }

}
