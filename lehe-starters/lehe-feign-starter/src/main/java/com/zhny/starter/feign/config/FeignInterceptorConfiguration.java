package com.zhny.starter.feign.config;

import cn.hutool.core.util.StrUtil;
import com.zhny.starter.common.constant.SystemConstant;
import com.zhny.starter.common.constant.TraceConstant;
import com.zhny.starter.common.utils.TenantContextHolder;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * feign拦截器
 *
 * @author pangu
 * 2020-9-9
 */
@Slf4j
public class FeignInterceptorConfiguration {

    /**
     * 使用feign client发送请求时，传递tenantId和traceId
     *
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            //传递tenantId
            if (null != TenantContextHolder.getUser()) {
                requestTemplate.header(SystemConstant.TENANT_ID, TenantContextHolder.getUser().getTenantId());
            }
            //传递日志traceId
            String traceId = MDC.get(TraceConstant.TRACE_ID);
            if (StrUtil.isBlank(traceId)) {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    HttpServletRequest request = attributes.getRequest();
                    Enumeration<String> headerNames = request.getHeaderNames();
                    if (headerNames != null) {
                        String headerName = null;
                        while (headerNames.hasMoreElements()) {
                            headerName = headerNames.nextElement();
                            if (headerName.equalsIgnoreCase(TraceConstant.TRACE_ID)) {
                                traceId = request.getHeader(headerName);
                                requestTemplate.header(TraceConstant.TRACE_ID, traceId);
                                MDC.put(TraceConstant.TRACE_ID, traceId);
                            }
                            String values = request.getHeader(headerName);
                            requestTemplate.header(headerName, values);
                        }
                    }
                }
            } else {
                if (StrUtil.isNotBlank(traceId)) {
                    requestTemplate.header(TraceConstant.TRACE_ID, traceId);
                }

            }
        };
    }
}
