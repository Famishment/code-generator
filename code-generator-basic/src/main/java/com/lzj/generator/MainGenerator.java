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
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("Tom");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("Sum = ");
        combineGenerate(mainTemplateConfig);
    }

    /**
     * 生成
     * @throws TemplateException
     * @throws IOException
     */
    public static void combineGenerate(Object model) throws TemplateException, IOException {

        String inputRootPath = "E:\\PlanetProject\\code-generator\\code-generator-demo-projects\\acm-template-pro";
        String outputRootPath = "E:\\PlanetProject\\code-generator";

        String inputPath;
        String outputPath;

        inputPath = new File(inputRootPath, "src/com/lzj/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/com/lzj/acm/MainTemplate.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, ".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath, ".gitignore").getAbsolutePath();
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);

        inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
        outputPath = new File(outputRootPath, "README.md").getAbsolutePath();
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);

        /*String projectPath = System.getProperty("user.dir");
        // 1.静态文件生成
        File parentFile = new File(projectPath).getParentFile();
        String inputPath = new File(parentFile, "code-generator-demo-projects" + File.separator + "acm-template").getAbsolutePath();
        String outputPath = projectPath;
        System.out.println("静态文件 - 输入路径：" + inputPath);
        System.out.println("静态文件 - 输出路径：" + outputPath);
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);
        // 2.动态文件生成
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/template/mainTemplate.java.ftl";
        String outputDynamicFilePath = projectPath + File.separator + "acm-template/src/com/lzj/acm/MainTemplate.java";
        System.out.println("动态文件 - 模板文件位置：" + inputDynamicFilePath);
        System.out.println("动态文件 - 目标文件位置：" + outputDynamicFilePath);
        DynamicGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);*/
    }
}
