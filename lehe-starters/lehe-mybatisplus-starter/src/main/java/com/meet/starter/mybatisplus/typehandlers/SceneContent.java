package com.lehe.starter.mybatisplus.typehandlers;

import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.util.List;

/**
 * @author 2020
 * 2021-05-20 18:00
 */
@Data
@MappedJdbcTypes(JdbcType.VARCHAR)  // 数据库类型
@MappedTypes({List.class})          // java数据类型
public class SceneContent {
    /**
     * 条件类型：等于、不等于...
     */
    private String type;
    /**
     * 字段分类(字段类型 1 单行文本 2 多行文本 3 单选 4日期 5 数字 6 小数 7 手机  8 文件 9 多选 10 人员 11 附件 12 部门 13 日期时间 14 邮箱 15客户 16 商机 17 联系人 18 地图 19 产品类型 20 合同 21 回款计划)
     */
    private String formType;
    /**
     * 字段英文名称
     */
    private String fieldName;
    /**
     * 用户输入的值
     */
    private String values;
}
