package com.lehe.starter.mybatisplus.typehandlers;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.io.IOException;
import java.util.List;

/**
 * @author 2020
 * @date 2021-11-23 10:19
 */
@MappedTypes({List.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class ListStringTypeHandler extends JacksonTypeHandler {

    public ListStringTypeHandler(Class<?> type) {
        super(type);
    }

    @Override
    protected List<String> parse(String json) {
        try {
            return getObjectMapper().readValue(json, new TypeReference<List<String>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
