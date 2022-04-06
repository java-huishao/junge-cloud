package com.zhny.admin.common;


import com.lehe.starter.log.common.SysLogEntity;
import com.lehe.starter.log.event.SysLogEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author houqj
 * 2021-01-17 12:29
 */
@Slf4j
@Component
@AllArgsConstructor
public class SysLogEventListener {

    @Async
    @EventListener
    public void saveSysLog(SysLogEvent sysLogEvent) {
        List<SysLogEntity> sysLogEntitys = sysLogEvent.getSysLogEntitys();
        for (SysLogEntity sysLogEntity : sysLogEntitys) {
            log.info("sysLogEntity==>{}", sysLogEntity);
        }
    }
}
