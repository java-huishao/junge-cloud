package com.zhny.admin.mapper;

import com.zhny.admin.entity.AdminPermission;
import com.lehe.common.model.MenuVo;
import com.lehe.starter.mybatisplus.base.BaseMpMapper;

import java.util.List;

/**
 * <p>
 * 后台权限表 Mapper 接口
 * </p>
 *
 * @author HOU
 * @since 2021-09-16
 */
public interface IAdminPermissionMapper extends BaseMpMapper<AdminPermission> {
    /**
     * 根据用户id查询菜单列表
     *
     * @param id
     * @return
     */
    List<MenuVo> getMenusListByUserId(String id);

    /**
     * 根据用户id查询所拥有的按钮列表
     *
     * @param id
     * @return
     */
    List<String> getPermissionBtnsByUserId(String id);

    /**
     * 根据用户id查询所有可按钮访问权限列表
     *
     * @param id
     * @return
     */
    List<String> getPermissionByUserId(String id);
}