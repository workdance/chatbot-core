package com.workdance.chatbot.mybatisPlus;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * MySQL 代码生成
 *
 * @author lanjerry
 * @since 3.5.3
 */

public class MySQLGeneratorTest extends BaseGeneratorTest {
    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/richplayground?useUnicode=true", "root", "U5i3C572")
            .schema("ai_chatbot_knowledge")
            .build();

    @Test
    public void testSimple() throws IOException {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig().entityBuilder().enableFileOverride().build());
        generator.strategy(strategyConfig().controllerBuilder().enableFileOverride().build());
        generator.strategy(strategyConfig().serviceBuilder().enableFileOverride().build());
        generator.global(globalConfig().author("michael.sl").outputDir(getCodegenFolderPath()).build());
        generator.execute();
    }


    public String getCodegenFolderPath() throws IOException {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        return Paths.get(s, "codegen").toString();
    }

    @Test
    public void getCodeFilePath() throws Exception {
        System.out.println(getCodegenFolderPath());
    }

}
