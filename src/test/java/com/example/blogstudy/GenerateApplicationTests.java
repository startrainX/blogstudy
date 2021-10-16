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
    private String dataSourceurl;

    @Value("${datasource.username}")
    private String dataSourcename;

    @Value("${datasource.password}")
    private String dataSourcepassword;

    @Value("${datasource.driver-class-name}")
    private String dataSourcedriver;

    @Value("${datatables.name}")
    private String tables;

    @Value("${package.parent}")
    private String packageParent;

//    @Value("${datatables.isNormalize}")
//    private boolean isNormalize;

    @Test
    void generateMybatisPlusTest() {
        new GenerateMybatisPlus().generate(
                dataSourceurl,
                dataSourcename,
                dataSourcepassword,
                dataSourcedriver,
                tables,
                packageParent,
                false);
    }
}