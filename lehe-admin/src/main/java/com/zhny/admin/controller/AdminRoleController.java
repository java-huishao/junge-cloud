package com.zhny.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhny.admin.entity.AdminRole;
import com.zhny.admin.service.IAdminRoleService;
import com.zhny.starter.common.result.Result;
import com.lehe.starter.mybatisplus.base.BaseController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 后台角色表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2021-09-16
 */
@Slf4j
@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/admin-role")
@AllArgsConstructor
public class AdminRoleController extends BaseController<IAdminRoleService, AdminRole, String> {
    private final IAdminRoleService AdminRoleService;

    /**
     * 聚合id查询 业务性质
     * 可能多表关联查询，一对多信息查询，复杂查询场景
     *
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/get/{id}")
    public Result<AdminRole> getById(@PathVariable(name = "id") String id) {
        return Result.success(AdminRoleService.getById(id));
    }

    /**
     * 列表
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/list")
    public Result<List<AdminRole>> list() {
        return Result.success(AdminRoleService.list());
    }

    /**
     * 分页列表 业务性质 多表信息展示 自定义参数
     *
     * @param current 当前页数
     * @param size    页码
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/pageList")
    public Result<IPage<AdminRole>> page(@RequestParam(name = "current") Long current, @RequestParam(name = "size") Long size) {
        IPage<AdminRole> page = new Page<>(current, size);
        return Result.success(AdminRoleService.page(page));
    }

    /**
     * 添加数据 业务性质
     *
     * @param entity 实体类
     * @return Result 返回结果 返回结果
     */
    @PostMapping(value = "/add")
    public Result<Boolean> addAdminRole(@RequestBody AdminRole entity) {
        return Result.success(AdminRoleService.save(entity));
    }

    /**
     * 修改数据 业务性质
     *
     * @return Result 返回结果
     */
    @PostMapping(value = "/modify")
    public Result<Boolean> modifyAdminRole(@RequestBody AdminRole entity) {
        return Result.success(AdminRoleService.updateById(entity));
    }

    /**
     * 修改数据 业务性质
     *
     * @return Result 返回结果
     */
    @PostMapping(value = "/setDefault")
    public Result<Boolean> setDefault(@RequestBody AdminRole entity) {
        AdminRoleService.update(lambdaUpdateWrapper().set(AdminRole::getDef, 0));
        return Result.success(AdminRoleService.updateById(entity));
    }

    /**
     * 删除一条数据 业务性质
     *
     * @return Result 返回结果
     */
    @DeleteMapping(value = "/delById/{id}")
    public Result<Boolean> delById(@PathVariable(name = "id") String id) {
        return Result.success(AdminRoleService.removeById(id));
    }
}
