package com.lzj.maker.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.lzj.maker.generator.file.DynamicFileGenerator;
import com.lzj.maker.meta.Meta;
import com.lzj.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @Auther: lzj
 * @Date: 2024/7/14-07-14-10:43
 * @Description: com.lzj.maker.generator
 */

public class MainGenerator {

    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMetaObject();
        //System.out.println(meta);

        // 输出的路经
        // 1. 项目根路径: E:\PlanetProject\code-generator\code-generator-maker
        String projectPath = System.getProperty("user.dir");
        // 2. 生成文件的输出路径: E:\PlanetProject\code-generator\code-generator-maker\generated
        String outputPath = projectPath + File.separator + "generated";
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }
        // 3. Java 包的基础路径：com.lzj
        String outputBasePackage = meta.getBasePackage();
        // 4. 转换为 com/lzj.
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
        // 5. E:\PlanetProject\code-generator\code-generator-maker\generated\src/main/java/com/lzj
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java/" + outputBasePackagePath;


        // 模板文件的路径
        // 1. 读取 resource 目录，meta.json文件所在路径: E:/PlanetProject/code-generator/code-generator-maker/target/classes/
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();


        String inputFilePath;
        String outputFilePath;

        // 生成 DataModel 数据模型类
        inputFilePath = inputResourcePath + File.separator + "/template/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // 生成子命令类
        // cli.command.ConfigCommand
        inputFilePath = inputResourcePath + File.separator + "/template/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        // cli.command.GeneratorCommand
        inputFilePath = inputResourcePath + File.separator + "/template/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        // cli.command.ListCommand
        inputFilePath = inputResourcePath + File.separator + "/template/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        // cli.CommandExecutor
        inputFilePath = inputResourcePath + File.separator + "/template/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        // Main
        inputFilePath = inputResourcePath + File.separator + "/template/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.DynamicGenerator
        inputFilePath = inputResourcePath + File.separator + "/template/java/generator/DynamicGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/generator/DynamicGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.MainGenerator
        inputFilePath = inputResourcePath + File.separator + "/template/java/generator/MainGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/generator/MainGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.StaticGenerator
        inputFilePath = inputResourcePath + File.separator + "/template/java/generator/StaticGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/generator/StaticGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // pom.xml
        inputFilePath = inputResourcePath + File.separator + "/template/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // 构建 jar 包
        JarGenerator.doGenerate(outputPath);

        // 封装脚本
        String shellOutputPath = outputPath + File.separator + "generator";
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        ScriptGenerator.doGenerate(shellOutputPath, jarPath);

    }

}
