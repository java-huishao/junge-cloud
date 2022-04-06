package com.lehe.starter.mybatisplus.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.entity.AbstractEntity;
import com.diboot.core.service.BaseService;
import com.zhny.starter.common.constant.TraceConstant;
import com.zhny.starter.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author houqj
 * @date 2018/4/17 11:08
 */
@Slf4j
public abstract class BaseController<K extends BaseMpService<T>, T extends AbstractEntity<Long>, ID extends Serializable> extends BaseCrudRestController<T> {
    /**
     * 获取service
     */
    @Autowired
    private K baseService;

    @Override
    protected BaseService getService() {
        return (BaseService) baseService;
    }

    public Result<T> get(ID id) {
        return Result.success(baseService.getById(id));
    }

    public Result<Object> delAllByIds(ID[] ids) {
        boolean b = false;
        for (ID id : ids) {
            b = baseService.removeById(id);
        }
        return Result.success(b ? "批量删除成功" : "批量删除失败");
    }

    public QueryWrapper<T> queryWrapper() {
        return new QueryWrapper<>();
    }

    public UpdateWrapper<T> updateWrapper() {
        return new UpdateWrapper<>();
    }

    public LambdaQueryWrapper<T> lambdaQueryWrapper() {
        return new LambdaQueryWrapper<>();
    }

    public LambdaUpdateWrapper<T> lambdaUpdateWrapper() {
        return new LambdaUpdateWrapper<>();
    }

    public Result<Object> success(){
        Result<Object> success = Result.success();
        success.setTId(MDC.get(TraceConstant.TRACE_ID));
        return success;
    }
    public <R> Result<R> success(R r){
        Result<R> success = Result.success();
        success.setData(r);
        success.setTId(MDC.get(TraceConstant.TRACE_ID));
        return success;
    }
}
