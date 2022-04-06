package com.zhny.starter.log.event;

import com.zhny.starter.log.common.SysLogEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * @author houqijun
 * 系统日志事件
 * 事件只用发布出去，由相应服务去消费，在这里不做持久化存储，由具体服务决定
 */
public class SysLogEvent extends ApplicationEvent {
    @Getter
    private final List<SysLogEntity> sysLogEntitys;

    public SysLogEvent(Object source, List<SysLogEntity> sysLogEntitys) {
        super(source);
        this.sysLogEntitys = sysLogEntitys;
    }
}
