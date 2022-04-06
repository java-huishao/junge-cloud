package com.zhny.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhny.admin.entity.SystemOperateRecord;
import com.zhny.admin.service.ISystemOperateRecordService;
import com.zhny.starter.common.result.Result;
import com.lehe.starter.mybatisplus.base.BaseController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统日志表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2021-09-22
 */
@Slf4j
@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/system-operate-record")
@AllArgsConstructor
public class SystemOperateRecordController extends BaseController<ISystemOperateRecordService, SystemOperateRecord, String> {
    private final ISystemOperateRecordService SystemOperateRecordService;

    /**
     * 聚合id查询 业务性质
     * 可能多表关联查询，一对多信息查询，复杂查询场景
     *
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/get/{id}")
    public Result<SystemOperateRecord> getById(@PathVariable(name = "id") String id) {
        return Result.success(SystemOperateRecordService.getById(id));
    }

    /**
     * 分页列表 业务性质 多表信息展示 自定义参数
     *
     * @param current 当前页数
     * @param size    页码
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/pageList")
    public Result<IPage<SystemOperateRecord>> page(@RequestParam(name = "current") Long current, @RequestParam(name = "size") Long size) {
        IPage<SystemOperateRecord> page = new Page<>(current, size);
        return Result.success(SystemOperateRecordService.page(page));
    }

    /**
     * 添加数据 业务性质
     *
     * @param entity 实体类
     * @return Result 返回结果 返回结果
     */
    @PostMapping(value = "/add")
    public Result<Boolean> addSystemOperateRecord(@RequestBody SystemOperateRecord entity) {
        return Result.success(SystemOperateRecordService.save(entity));
    }

    /**
     * 修改数据 业务性质
     *
     * @return Result 返回结果
     */
    @PostMapping(value = "/modify")
    public Result<Boolean> modifySystemOperateRecord(@RequestBody SystemOperateRecord entity) {
        return Result.success(SystemOperateRecordService.updateById(entity));
    }

    /**
     * 删除一条数据 业务性质
     *
     * @return Result 返回结果
     */
    @DeleteMapping(value = "/delById/{id}")
    public Result<Boolean> delById(@PathVariable(name = "id") String id) {
        return Result.success(SystemOperateRecordService.removeById(id));
    }
}
