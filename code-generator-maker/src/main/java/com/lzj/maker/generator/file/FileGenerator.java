package com.lzj.maker.generator.file;

import com.lzj.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @Auther: lzj
 * @Date: 2024/4/14-04-14-11:57
 * @Description: com.lzj.generator
 */

public class FileGenerator {

    public static void main(String[] args) throws TemplateException, IOException {
        // 动静结合，生成静态、动态文件
        DataModel dataModel = new DataModel();
        dataModel.setAuthor("Tom");
        dataModel.setLoop(false);
        dataModel.setOutputText("Sum = ");
        combineGenerate(dataModel);
    }

    /**
     * 生成
     * @throws TemplateException
     * @throws IOException
     */
    public static void combineGenerate(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        // 1.静态文件生成
        File parentFile = new File(projectPath).getParentFile();
        String inputPath = new File(parentFile, "code-generator-demo-projects" + File.separator + "acm-template").getAbsolutePath();
        String outputPath = projectPath;
        System.out.println("静态文件 - 输入路径：" + inputPath);
        System.out.println("静态文件 - 输出路径：" + outputPath);
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
        // 2.动态文件生成
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/template/mainTemplate.java.ftl";
        String outputDynamicFilePath = projectPath + File.separator + "acm-template/src/com/lzj/acm/MainTemplate.java";
        System.out.println("动态文件 - 模板文件位置：" + inputDynamicFilePath);
        System.out.println("动态文件 - 目标文件位置：" + outputDynamicFilePath);
        DynamicFileGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
    }
}
