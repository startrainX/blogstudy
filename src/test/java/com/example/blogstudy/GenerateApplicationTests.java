package com.example.blogstudy;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/8/11 14:01
 * @description:
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:generate.properties")
class GenerateApplicationTests {

    @Value("${datasource.url}")
    private String dataSourceUrl;

    @Value("${datasource.username}")
    private String dataSourceName;

    @Value("${datasource.password}")
    private String dataSourcePassword;

    @Value("${datasource.driver-class-name}")
    private String dataSourceDriver;

    @Value("${datatables.name}")
    private String tables;

    @Value("${package.parent}")
    private String packageParent;

//    @Value("${datatables.isNormalize}")
//    private boolean isNormalize;

    @Test
    void generateMybatisPlusTest() {
        new GenerateMybatisPlus().generate(
                dataSourceUrl,
                dataSourceName,
                dataSourcePassword,
                dataSourceDriver,
                tables,
                packageParent,
                false);
    }
}