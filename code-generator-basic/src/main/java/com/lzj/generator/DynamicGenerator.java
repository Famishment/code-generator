package com.lzj.generator;

import com.lzj.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 静态文件生成器
 */

public class DynamicGenerator {

    public static void main(String[] args) throws IOException, TemplateException {
        //
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        //
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/template"));
        //
        configuration.setDefaultEncoding("utf-8");
        //
        Template template = configuration.getTemplate("MainTemplate.java.ftl");
        //
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("Jerry");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");
        //
        Writer out = new FileWriter("MainTemplate.java");
        template.process(mainTemplateConfig, out);
        //
        out.close();
    }

}
