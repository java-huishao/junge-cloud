package com.lehe.starter.mybatisplus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Properties;

/**
 * @author 2020
 * @date 2021-12-21 10:58
 * https://baomidou.com/pages/981406/#%E6%95%B0%E6%8D%AE%E5%BA%93%E9%85%8D%E7%BD%AE-datasourceconfig
 */
public class LeheGenerator {

    public static void generator() {
        com.lehe.starter.mybatisplus.GeneratorProperties properties = loadProperties();
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(properties.getUrl(), properties.getUsername(), properties.getPassword())
                .dbQuery(new MySqlQuery())
                .schema("mybatis-plus")
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler())
                .build();
        /**
         * 数据源配置
         */
        AutoGenerator generator = new AutoGenerator(dataSourceConfig);
        generator.global(globalConfig(properties));
        generator.strategy(strategyConfig(properties));
        generator.template(templateConfig());
        generator.packageInfo(packageConfig(properties));
        generator.injection(injectionConfig());
        generator.execute();
        System.err.println(generator.getInjectionConfig().getCustomMap().get("test"));
        System.exit(0);
    }

    /**
     * 注入配置(InjectionConfig)
     *
     * @return
     */
    private static InjectionConfig injectionConfig() {
        return new InjectionConfig.Builder()
                .beforeOutputFile((tableInfo, objectMap) -> {
                    System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
                })
                .customMap(Collections.singletonMap("test", "baomidou"))
//                .customFile(Collections.singletonMap("test.txt", "/templates/test.vm"))
                .build();
    }

    private static GlobalConfig globalConfig(com.lehe.starter.mybatisplus.GeneratorProperties properties) {
        return new GlobalConfig.Builder()
                .fileOverride()
                .disableOpenDir()//禁止打开输出目录 默认值:tru
                .outputDir(properties.getWorkSpace() + "\\src\\main\\java")
                .author(properties.getAuthor())
                .dateType(DateType.TIME_PACK)
                .commentDate("yyyy-MM-dd HH:mm:ss")
                .build();
    }

    private static PackageConfig packageConfig(com.lehe.starter.mybatisplus.GeneratorProperties properties) {
        return new PackageConfig.Builder()
                .parent(properties.getPackageName())
                .entity("entity")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("mapper")
                .xml("mapper.xml")
                .controller("controller")
                .other("other")
                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, properties.getWorkSpace() + "\\src\\main\\resources\\mapper"))
                .build();
    }

    private static TemplateConfig templateConfig() {
        return new TemplateConfig.Builder()
                .disable(TemplateType.ENTITY)
                .entity("/templates/entity.java.vm")
                .service("/templates/service.java.vm")
                .serviceImpl("/templates/serviceImpl.java.vm")
                .mapper("/templates/mapper.java.vm")
                .mapperXml("/templates/mapper.xml.vm")
                .controller("/templates/controller.java.vm")
                .build();
    }


    /**
     * Mapper 策略配置
     *
     * @return
     */
    private static StrategyConfig strategyConfig(com.lehe.starter.mybatisplus.GeneratorProperties properties) {
        StrategyConfig build = new StrategyConfig.Builder()
                .enableCapitalMode()
                .enableSkipView()
                .disableSqlFilter()
//                .likeTable(new LikeTable("USER"))
                .addInclude(properties.getTables())
                /*Entity 策略配置*/
                .entityBuilder()
                .superClass(BaseEntity.class)
                .disableSerialVersionUID()
                .enableChainModel()
                .enableLombok()
                .enableRemoveIsPrefix()//开启 Boolean 类型字段移除 is 前缀
                .naming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略 默认下划线转驼峰命名:NamingStrategy.underline_to_camel
                .columnNaming(NamingStrategy.underline_to_camel)//数据库表字段映射到实体的命名策略
                .addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time")
                .addTableFills(new Column("create_time", FieldFill.INSERT))
                .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                .idType(IdType.AUTO)
                .formatFileName("%s")
                /*Controller 策略配置*/
                .controllerBuilder()
                .superClass(BaseController.class)
                .enableHyphenStyle()
                .enableRestStyle()
                .formatFileName("%sController")
                /*Service 策略配置*/
                .serviceBuilder()
                .superServiceClass(com.lehe.starter.mybatisplus.base.BaseMpService.class)
                .superServiceImplClass(com.lehe.starter.mybatisplus.base.BaseMpServiceImpl.class)
                .formatServiceFileName("I%sService")
                .formatServiceImplFileName("%sServiceImpl")
                /*Mapper 策略配置*/
                .mapperBuilder()
                .superClass(com.lehe.starter.mybatisplus.base.BaseMpMapper.class)
                .enableBaseResultMap()
                .enableBaseColumnList()
                .formatMapperFileName("%sMapper")
                .formatXmlFileName("%sMapper")
                .build();
        return build;
    }

    private static com.lehe.starter.mybatisplus.GeneratorProperties loadProperties() {
        com.lehe.starter.mybatisplus.GeneratorProperties generatorProperties = new com.lehe.starter.mybatisplus.GeneratorProperties();
        String author = "";
        String workspace = "";
        String PACKAGENAME = "";
        String[] TABLES = new String[]{};

        String driverName = "";
        String url = "";
        String username = "";
        String password = "";
        Properties properties = new Properties();
        try {
            // 使用ClassLoader加载properties配置文件生成对应的输入流
            InputStream in = com.lehe.starter.mybatisplus.LeheGenerator.class.getClassLoader().getResourceAsStream("generator.properties");
            // 使用properties对象加载输入流
            properties.load(in);
            //获取key对应的value值
            author = new String(properties.getProperty("author").getBytes(StandardCharsets.ISO_8859_1), "gbk");
            workspace = new String(properties.getProperty("workspace").getBytes(StandardCharsets.ISO_8859_1), "gbk");
            String tables = new String(properties.getProperty("tables").getBytes(StandardCharsets.ISO_8859_1), "gbk");
            PACKAGENAME = new String(properties.getProperty("packageName").getBytes(StandardCharsets.ISO_8859_1), "gbk");
            TABLES = tables.split(",");


            driverName = new String(properties.getProperty("driverName").getBytes(StandardCharsets.ISO_8859_1), "gbk");
            url = new String(properties.getProperty("url").getBytes(StandardCharsets.ISO_8859_1), "gbk");
            username = new String(properties.getProperty("username").getBytes(StandardCharsets.ISO_8859_1), "gbk");
            password = new String(properties.getProperty("password").getBytes(StandardCharsets.ISO_8859_1), "gbk");


            generatorProperties.setAuthor(author);
            generatorProperties.setWorkSpace(workspace);
            generatorProperties.setPackageName(PACKAGENAME);
            generatorProperties.setTables(TABLES);
            generatorProperties.setDriverName(driverName);
            generatorProperties.setUrl(url);
            generatorProperties.setUsername(username);
            generatorProperties.setPassword(password);
            return generatorProperties;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return generatorProperties;
    }

}
