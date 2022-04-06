package com.lehe.starter.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zhny.starter.common.utils.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author houqj
 * 2019-08-06 17:13
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (null != TenantContextHolder.getUser()) {
            log.debug("添加操作:字段填充当前用户{}", TenantContextHolder.getUser());
            this.setFieldValByName("createrId", TenantContextHolder.getUser().getId(), metaObject);
            this.setFieldValByName("createBy", TenantContextHolder.getUser().getName(), metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (null != TenantContextHolder.getUser()) {
            log.debug("修改操作:字段填充当前用户{}", TenantContextHolder.getUser());
            this.setFieldValByName("updaterId", TenantContextHolder.getUser().getId(), metaObject);
            this.setFieldValByName("updateBy", TenantContextHolder.getUser().getName(), metaObject);
        }
    }
}
