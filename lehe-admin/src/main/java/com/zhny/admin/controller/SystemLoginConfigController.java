package com.zhny.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhny.admin.entity.SystemLoginConfig;
import com.zhny.admin.service.ISystemLoginConfigService;
import com.zhny.starter.common.result.Result;
import com.lehe.starter.mybatisplus.base.BaseController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 登录背景配置表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2021-09-22
 */
@Slf4j
@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/system-login-config")
@AllArgsConstructor
public class SystemLoginConfigController extends BaseController<ISystemLoginConfigService, SystemLoginConfig, String> {
    private final ISystemLoginConfigService SystemLoginConfigService;

    /**
     * 聚合id查询 业务性质
     * 可能多表关联查询，一对多信息查询，复杂查询场景
     *
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/get/{id}")
    public Result<SystemLoginConfig> getById(@PathVariable(name = "id") String id) {
        return Result.success(SystemLoginConfigService.getById(id));
    }

    /**
     * 分页列表 业务性质 多表信息展示 自定义参数
     *
     * @param current 当前页数
     * @param size    页码
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/pageList")
    public Result<IPage<SystemLoginConfig>> page(@RequestParam(name = "current") Long current, @RequestParam(name = "size") Long size) {
        IPage<SystemLoginConfig> page = new Page<>(current, size);
        return Result.success(SystemLoginConfigService.page(page));
    }

    /**
     * 添加数据 业务性质
     *
     * @param entity 实体类
     * @return Result 返回结果 返回结果
     */
    @PostMapping(value = "/add")
    public Result<Boolean> addSystemLoginConfig(@RequestBody SystemLoginConfig entity) {
        return Result.success(SystemLoginConfigService.save(entity));
    }

    /**
     * 修改数据 业务性质
     *
     * @return Result 返回结果
     */
    @PostMapping(value = "/modify")
    public Result<Boolean> modifySystemLoginConfig(@RequestBody SystemLoginConfig entity) {
        return Result.success(SystemLoginConfigService.updateById(entity));
    }

    /**
     * 删除一条数据 业务性质
     *
     * @return Result 返回结果
     */
    @DeleteMapping(value = "/delById/{id}")
    public Result<Boolean> delById(@PathVariable(name = "id") String id) {
        return Result.success(SystemLoginConfigService.removeById(id));
    }
}
