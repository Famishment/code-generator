package com.lzj.generator;

import com.lzj.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @Auther: lzj
 * @Date: 2024/4/14-04-14-11:57
 * @Description: com.lzj.generator
 */

public class MainGenerator {

    public static void main(String[] args) throws TemplateException, IOException {
        // 动静结合，生成静态、动态文件
        combineGenerate();
    }

    /**
     * 生成
     * @throws TemplateException
     * @throws IOException
     */
    public static void combineGenerate() throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        // 1.静态文件生成
        String inputPath = new File(projectPath, "code-generator-demo-projects" + File.separator + "acm-template").getAbsolutePath();System.out.println();
        String outputPath = projectPath;
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);
        // 2.动态文件生成
        String inputDynamicFilePath = projectPath + File.separator + "code-generator-basic/src/main/resources/template/mainTemplate.java.ftl";
        String outputDynamicFilePath = projectPath + File.separator + "acm-template/src/com/lzj/acm/MainTemplate.java";
        // 数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("Tom");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("Sum = ");
        DynamicGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, mainTemplateConfig);
    }
}
