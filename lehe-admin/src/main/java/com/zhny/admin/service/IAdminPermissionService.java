package com.zhny.admin.service;

import com.zhny.admin.entity.AdminPermission;
import com.lehe.starter.mybatisplus.base.BaseMpService;

import java.util.List;

/**
 * <p>
 * 后台权限表 接口
 * </p>
 *
 * @author HOU
 * @since 2021-09-16
 */
public interface IAdminPermissionService extends BaseMpService<AdminPermission> {

    /**
     * 根据用户id查询所拥有的按钮列表
     * <p>
     * /**
     * 根据用户id查询所有可按钮访问权限列表
     *
     * @param id
     * @return
     */
    List<String> getPermissionByUserId(String id);

}
