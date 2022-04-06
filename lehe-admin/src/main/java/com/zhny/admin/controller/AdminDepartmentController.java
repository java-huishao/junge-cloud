package com.zhny.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhny.admin.entity.AdminDepartment;
import com.zhny.admin.service.IAdminDepartmentService;
import com.zhny.starter.common.result.Result;
import com.lehe.starter.mybatisplus.base.BaseController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统部门表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2021-09-22
 */
@Slf4j
@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/admin-department")
@AllArgsConstructor
public class AdminDepartmentController extends BaseController<IAdminDepartmentService, AdminDepartment, String> {
    private final IAdminDepartmentService AdminDepartmentService;

    /**
     * 聚合id查询 业务性质
     * 可能多表关联查询，一对多信息查询，复杂查询场景
     *
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/get/{id}")
    public Result<AdminDepartment> getById(@PathVariable(name = "id") String id) {
        return Result.success(AdminDepartmentService.getById(id));
    }

    /**
     * 根据父id查询
     * 可能多表关联查询，一对多信息查询，复杂查询场景
     *
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/getByParentId/{parentId}")
    public Result<List<AdminDepartment>> getByParentId(@PathVariable(name = "parentId") String parentId) {
        return Result.success(AdminDepartmentService.list(lambdaQueryWrapper().eq(AdminDepartment::getParentId, parentId)));
    }

    /**
     * 分页列表 业务性质 多表信息展示 自定义参数
     *
     * @param current 当前页数
     * @param size    页码
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/pageList")
    public Result<IPage<AdminDepartment>> page(@RequestParam(name = "current") Long current, @RequestParam(name = "size") Long size) {
        IPage<AdminDepartment> page = new Page<>(current, size);
        return Result.success(AdminDepartmentService.page(page));
    }

    /**
     * 添加数据 业务性质
     *
     * @param entity 实体类
     * @return Result 返回结果 返回结果
     */
    @PostMapping(value = "/add")
    public Result<Boolean> addAdminDepartment(@RequestBody AdminDepartment entity) {
        return Result.success(AdminDepartmentService.save(entity));
    }

    /**
     * 修改数据 业务性质
     *
     * @return Result 返回结果
     */
    @PostMapping(value = "/modify")
    public Result<Boolean> modifyAdminDepartment(@RequestBody AdminDepartment entity) {
        return Result.success(AdminDepartmentService.updateById(entity));
    }

    /**
     * 删除一条数据 业务性质
     *
     * @return Result 返回结果
     */
    @DeleteMapping(value = "/delById/{id}")
    public Result<Boolean> delById(@PathVariable(name = "id") String id) {
        return Result.success(AdminDepartmentService.removeById(id));
    }
}
