package com.zhny.starter.web.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhny.starter.common.constant.SystemConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Component
public class TraceLogSender {

    //请求路径
    public static final String trace_key_request_url = "requestUrl";
    //请求get参数
    public static final String trace_key_get_param = "paramGet";
    //请求post参数
    public static final String trace_key_post_param = "paramPost";
    //服务名称
    public static final String trace_key_app_name = "appName";
    //请求的sql
    public static final String trace_key_sql = "sql";
    //请求的traceId
    public static final String trace_key_trace_id = "traceId";
    //请求的上游父traceId
    public static final String trace_key_parent_trace_id = "parentTraceId";
    //执行结果result
    public static final String trace_key_result = "result";
    @Value("${lehe.trace.send.enabled:false}")
    public Boolean traceSendEnabled;
    @Value("${spring.application.name}")
    private String appName;
    @Resource
    private RestTemplate restTemplate;


    @Async
    public void sendTraceLog() {
        if (traceSendEnabled) {
            log.debug("发送链路追踪日志参数:{}", MDC.getCopyOfContextMap());
            ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(SystemConstant.LEHE_TRACE_URL, MDC.getCopyOfContextMap(), String.class);
            MDC.clear();
            log.debug("发送链路追踪日志结果:{}", stringResponseEntity);
        }
    }

    /**
     * 发送异常执行的方法
     *
     * @param e
     * @param request
     */
    private void handleExceptionToFeishuMsg(Exception e, HttpServletRequest request, String exceptionType) {
        //获取异常信息类,方法
        List<String> list = findExceptionClass(e);
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();

        String requestIP = "";
        if (request.getHeader("x-forwarded-for") == null) {
            requestIP = request.getRemoteAddr();
        } else {
            requestIP = request.getHeader("x-forwarded-for");
        }
        //判断环境,发送对应机器人
        String url = "https://open.feishu.cn/open-apis/bot/v2/hook/bfde3fea-256b-41a5-88d6-c4932f1b9852";
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
        JSONArray jsonArray = new JSONArray();
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject post = new JSONObject();
        JSONObject obj1 = new JSONObject();
        JSONObject zh_cn = new JSONObject();
        post.put("post", obj1);
        map.put("tag", "text");
        map.put("text", appName);
        list2.add(map);
        map = new HashMap<>();
        map.put("tag", "text");
        map.put("text", list.get(0));
        list2.add(map);
        map = new HashMap<>();
        map.put("tag", "text");
        map.put("text", list.get(1));
        list2.add(map);
        map = new HashMap<>();
        map.put("tag", "text");
        map.put("text", exceptionType);
        list2.add(map);
        map = new HashMap<>();
        map.put("tag", "text");
        map.put("text", requestURI);
        list2.add(map);
        map = new HashMap<>();
        map.put("tag", "text");
        map.put("text", requestIP);
        list2.add(map);
        if (null != e.getCause()) {
            map = new HashMap<>();
            map.put("tag", "text");
            map.put("text", e.getCause());
            list2.add(map);
        }
        if (StringUtils.isNotBlank(e.getMessage())) {
            map = new HashMap<>();
            map.put("tag", "text");
            map.put("text", e.getMessage());
            list2.add(map);
        }

        zh_cn.put("title", "告警");
        jsonArray.add(list2);
        zh_cn.put("content", jsonArray);
        obj1.put("zh_cn", zh_cn);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg_type", "post");
        jsonObject.put("content", post);

        HttpEntity httpEntity = new HttpEntity(jsonObject);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        log.debug("飞书发送:{}", stringResponseEntity);
    }


    private List<String> findExceptionClass(Exception e) {
        String exclass = "";
        String method = "";
        List<String> list = new ArrayList<>();
        StackTraceElement[] st = e.getStackTrace();
        for (StackTraceElement stackTraceElement : st) {
            exclass = stackTraceElement.getClassName();
            method = stackTraceElement.getMethodName();
            System.out.println(exclass);
            System.out.println(method);
            String[] strs = method.split("\\$");
            method = strs[0];
            System.out.println(new Date() + ":" + "[类:" + exclass + "]调用"
                    + method + "时在第" + stackTraceElement.getLineNumber());
            break;
        }

        list.add(exclass);
        list.add(method);
        return list;
    }

}
