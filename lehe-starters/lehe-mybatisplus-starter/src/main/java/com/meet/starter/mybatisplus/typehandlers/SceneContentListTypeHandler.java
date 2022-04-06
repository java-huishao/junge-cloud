package com.lehe.starter.mybatisplus.typehandlers;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.io.IOException;
import java.util.List;

/**
 * @author 2020
 * 2021-05-20 17:57
 */
@MappedTypes({Object.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class SceneContentListTypeHandler extends JacksonTypeHandler {

    public SceneContentListTypeHandler(Class<?> type) {
        super(type);
    }

    @Override
    protected Object parse(String json) {
        try {
            return new ObjectMapper().readValue(json, new TypeReference<List<SceneContent>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
