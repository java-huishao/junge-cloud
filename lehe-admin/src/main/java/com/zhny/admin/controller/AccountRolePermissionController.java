package com.zhny.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhny.admin.entity.AccountRolePermission;
import com.zhny.admin.service.IAccountRolePermissionService;
import com.zhny.starter.common.result.Result;
import com.lehe.starter.mybatisplus.base.BaseController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色权限表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2021-09-16
 */
@Slf4j
@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/account-role-permission")
@AllArgsConstructor
public class AccountRolePermissionController extends BaseController<IAccountRolePermissionService, AccountRolePermission, String> {
    private final IAccountRolePermissionService AccountRolePermissionService;

    /**
     * 聚合id查询 业务性质
     * 可能多表关联查询，一对多信息查询，复杂查询场景
     *
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/get/{id}")
    public Result<AccountRolePermission> getById(@PathVariable(name = "id") String id) {
        return Result.success(AccountRolePermissionService.getById(id));
    }

    /**
     * 聚合id查询 业务性质
     * 可能多表关联查询，一对多信息查询，复杂查询场景
     *
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/getPermissionIds")
    public Result<List<String>> getPermissionIds(@RequestParam(name = "roleId") String roleId) {
        return Result.success(AccountRolePermissionService.list(lambdaQueryWrapper().eq(AccountRolePermission::getRoleId, roleId)).stream().map(AccountRolePermission::getPermissionId).collect(Collectors.toList()));
    }

    /**
     * 分页列表 业务性质 多表信息展示 自定义参数
     *
     * @param current 当前页数
     * @param size    页码
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/pageList")
    public Result<IPage<AccountRolePermission>> page(@RequestParam(name = "current") Long current, @RequestParam(name = "size") Long size) {
        IPage<AccountRolePermission> page = new Page<>(current, size);
        return Result.success(AccountRolePermissionService.page(page));
    }

    /**
     * 添加数据 业务性质
     *
     * @param entity 实体类
     * @return Result 返回结果 返回结果
     */
    @PostMapping(value = "/add")
    public Result<Boolean> addAccountRolePermission(@RequestBody AccountRolePermission entity) {
        return Result.success(AccountRolePermissionService.save(entity));
    }

    /**
     * 修改数据 业务性质
     *
     * @return Result 返回结果
     */
    @PostMapping(value = "/modify")
    public Result<Boolean> modifyAccountRolePermission(@RequestBody AccountRolePermission entity) {
        return Result.success(AccountRolePermissionService.updateById(entity));
    }

    /**
     * 删除一条数据 业务性质
     *
     * @return Result 返回结果
     */
    @DeleteMapping(value = "/delById/{id}")
    public Result<Boolean> delById(@PathVariable(name = "id") String id) {
        return Result.success(AccountRolePermissionService.removeById(id));
    }
}
