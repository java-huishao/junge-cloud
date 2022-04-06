package com.zhny.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhny.admin.entity.AdminPermission;
import com.zhny.admin.service.IAdminPermissionService;
import com.lehe.common.constant.CommonConstant;
import com.zhny.starter.common.result.Result;
import com.lehe.starter.mybatisplus.base.BaseController;
import com.lehe.starter.mybatisplus.utils.TreeUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 后台权限表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2021-09-16
 */
@Slf4j
@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/admin-permission")
@AllArgsConstructor
public class AdminPermissionController extends BaseController<IAdminPermissionService, AdminPermission, String> {
    private final IAdminPermissionService AdminPermissionService;

    /**
     * 聚合id查询 业务性质
     * 可能多表关联查询，一对多信息查询，复杂查询场景
     *
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/get/{id}")
    public Result<AdminPermission> getById(@PathVariable(name = "id") String id) {
        return Result.success(AdminPermissionService.getById(id));
    }

    /**
     * 根据父id查询
     * 可能多表关联查询，一对多信息查询，复杂查询场景
     *
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/getByParentId/{parentId}")
    public Result<List<AdminPermission>> getByParentId(@PathVariable(name = "parentId") String parentId) {
        return Result.success(AdminPermissionService.list(lambdaQueryWrapper().eq(AdminPermission::getParentId, parentId)));
    }

    /**
     * 根据父id查询
     * 可能多表关联查询，一对多信息查询，复杂查询场景
     *
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/treeList")
    public Result<List<AdminPermission>> treeList() throws IllegalAccessException, InstantiationException {
        List<AdminPermission> treeList = new TreeUtil<>(AdminPermissionService.list()).getTreeList(CommonConstant.defaultTreeParentId, AdminPermission.class);
        return Result.success(treeList);
    }

    /**
     * 分页列表 业务性质 多表信息展示 自定义参数
     *
     * @param current 当前页数
     * @param size    页码
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/pageList")
    public Result<IPage<AdminPermission>> page(@RequestParam(name = "current") Long current, @RequestParam(name = "size") Long size) {
        IPage<AdminPermission> page = new Page<>(current, size);
        return Result.success(AdminPermissionService.page(page));
    }

    /**
     * 添加数据 业务性质
     *
     * @param entity 实体类
     * @return Result 返回结果 返回结果
     */
    @PostMapping(value = "/add")
    public Result<Boolean> addAdminPermission(@RequestBody AdminPermission entity) {
        return Result.success(AdminPermissionService.save(entity));
    }

    /**
     * 修改数据 业务性质
     *
     * @return Result 返回结果
     */
    @PostMapping(value = "/modify")
    public Result<Boolean> modifyAdminPermission(@RequestBody AdminPermission entity) {
        return Result.success(AdminPermissionService.updateById(entity));
    }

    /**
     * 删除一条数据 业务性质
     *
     * @return Result 返回结果
     */
    @DeleteMapping(value = "/delById/{id}")
    public Result<Boolean> delById(@PathVariable(name = "id") String id) {
        return Result.success(AdminPermissionService.removeById(id));
    }
}
