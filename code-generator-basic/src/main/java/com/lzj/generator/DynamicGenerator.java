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
 * 动态文件生成器
 */

public class DynamicGenerator {

    public static void main(String[] args) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir") + File.separator + "code-generator-basic";
        String inputPath = projectPath + File.separator + "src/main/resources/template/mainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate.java";

        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("Tom");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("输出结果：");

        doGenerate(inputPath, outputPath, mainTemplateConfig);
    }

    /**
     * 生成文件
     *
     * @param inputPath   模板文件路径
     * @param outputPath  输出路径
     * @param dataModel   数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath, String outputPath, Object dataModel) throws IOException, TemplateException {
        // 创建 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");
        configuration.setNumberFormat("0.######");

        // 创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        // 生成
        Writer out = new FileWriter(outputPath);
        template.process(dataModel, out);
        // 生成文件后关闭
        out.close();
    }

}
