package com.zhny.starter.log.common;


import com.zhny.starter.log.enums.ActionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Content {

    private final String object;
    private final String detail;
    private String subModel;
    private ActionEnum operateEnum;

    public Content(String object, String detail) {
        this.object = object;
        this.detail = detail;
    }

    public Content(String subModel, String object, String detail) {
        this.subModel = subModel;
        this.object = object;
        this.detail = detail;
    }

    public Content(String object, String detail, ActionEnum operateEnum) {
        this.object = object;
        this.detail = detail;
        this.operateEnum = operateEnum;
    }
}
