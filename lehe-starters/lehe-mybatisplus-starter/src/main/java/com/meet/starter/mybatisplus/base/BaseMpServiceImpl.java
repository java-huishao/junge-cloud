package com.lehe.starter.mybatisplus.base;

import com.diboot.core.service.BaseService;
import com.diboot.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseMpServiceImpl<K extends BaseMpMapper<T>, T> extends BaseServiceImpl<K, T> implements BaseMpService<T>, BaseService<T> {

    @Autowired
    protected K basempMapper;
}
