package com.zhny.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhny.admin.entity.SystemDictionary;
import com.zhny.admin.service.ISystemDictionaryService;
import com.zhny.starter.common.result.Result;
import com.lehe.starter.mybatisplus.base.BaseController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统字典分类表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2021-09-22
 */
@Slf4j
@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/system-dictionary")
@AllArgsConstructor
public class SystemDictionaryController extends BaseController<ISystemDictionaryService, SystemDictionary, String> {
    private final ISystemDictionaryService SystemDictionaryService;

    /**
     * 聚合id查询 业务性质
     * 可能多表关联查询，一对多信息查询，复杂查询场景
     *
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/get/{id}")
    public Result<SystemDictionary> getById(@PathVariable(name = "id") String id) {
        return Result.success(SystemDictionaryService.getById(id));
    }

    /**
     * 聚合id查询 业务性质
     * 可能多表关联查询，一对多信息查询，复杂查询场景
     *
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/getByType/{type}")
    public Result<List<SystemDictionary>> getByType(@PathVariable(name = "type") String type) {
        return Result.success(SystemDictionaryService.list(lambdaQueryWrapper().eq(SystemDictionary::getType, type).orderByAsc(SystemDictionary::getSort)));
    }

    /**
     * 分页列表 业务性质 多表信息展示 自定义参数
     *
     * @param current 当前页数
     * @param size    页码
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/pageList")
    public Result<IPage<SystemDictionary>> page(@RequestParam(name = "current") Long current, @RequestParam(name = "size") Long size) {
        IPage<SystemDictionary> page = new Page<>(current, size);
        return Result.success(SystemDictionaryService.page(page));
    }

    /**
     * 添加数据 业务性质
     *
     * @param entity 实体类
     * @return Result 返回结果 返回结果
     */
    @PostMapping(value = "/add")
    public Result<Boolean> addSystemDictionary(@RequestBody SystemDictionary entity) {
        return Result.success(SystemDictionaryService.save(entity));
    }

    /**
     * 修改数据 业务性质
     *
     * @return Result 返回结果
     */
    @PostMapping(value = "/modify")
    public Result<Boolean> modifySystemDictionary(@RequestBody SystemDictionary entity) {
        return Result.success(SystemDictionaryService.updateById(entity));
    }

    /**
     * 删除一条数据 业务性质
     *
     * @return Result 返回结果
     */
    @DeleteMapping(value = "/delById/{id}")
    public Result<Boolean> delById(@PathVariable(name = "id") String id) {
        return Result.success(SystemDictionaryService.removeById(id));
    }
}
