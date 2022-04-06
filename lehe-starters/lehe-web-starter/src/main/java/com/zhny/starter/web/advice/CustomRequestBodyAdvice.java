package com.zhny.starter.web.advice;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
/**
 * 描述:RequestBodyAdvice:解释
 * 允许在将请求的主体读取和转换成一个对象之前对请求进行自定义，
 * 并允许在将其传递到控制器方法作为一个@RequestBody或HttpEntity方法参数之前处理结果对象。
 * https://blog.csdn.net/niugang0920/article/details/80679096
 *
 * @author houqj
 * 2019/7/24
 **/

/**
 * @author houqj
 * 2019-07-24 11:08
 */
@Slf4j
@RestControllerAdvice
public class CustomRequestBodyAdvice implements RequestBodyAdvice {

    private static final String pattern = "(script)|(select)";

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @SneakyThrows
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        return new MyHttpInputMessage(httpInputMessage);
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    private String filterDangerString(String value) {
        if (value == null) {
            return null;
        }
        value = value.replaceAll("\\|", "");
        value = value.replaceAll("&", "");
        value = value.replaceAll(";", "");
        value = value.replaceAll("@", "");
        value = value.replaceAll("'", "");
        value = value.replaceAll("\\'", "");
        value = value.replaceAll("<", "");
        value = value.replaceAll("-", "");
        value = value.replaceAll(">", "");
        value = value.replaceAll("\\(", "");
        value = value.replaceAll("\\)", "");
        value = value.replaceAll("\\+", "");
        value = value.replaceAll("\r", "");
        value = value.replaceAll("\n", "");
        value = value.replaceAll("script", "");
        value = value.replaceAll("select", "");
        value = value.replaceAll("\"", "");
        value = value.replaceAll(">", "");
        value = value.replaceAll("<", "");
        value = value.replaceAll("=", "");
        value = value.replaceAll("/", "");
        return value;
    }

    class MyHttpInputMessage implements HttpInputMessage {
        private final HttpHeaders headers;
        private final InputStream body;

        @SuppressWarnings("unchecked")
        public MyHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
            String string = IOUtils.toString(inputMessage.getBody(), StandardCharsets.UTF_8);
            this.headers = inputMessage.getHeaders();
            this.body = IOUtils.toInputStream(string.replaceAll(pattern, ""), "UTF-8");
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }

}
