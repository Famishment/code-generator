package com.lzj.maker.generator;

import java.io.*;

/**
 * @Auther: lzj
 * @Date: 2024/7/14-07-14-18:46
 * @Description: com.lzj.maker.generator
 */

public class JarGenerator {

    public static void doGenerate(String projectDir) throws IOException, InterruptedException {
        // 调用 Process 类，执行 Maven 打包命令
        String winMavenCommand = "mvn.cmd clean package -DskipTests=true";
        String otherMavenCommand = "mvn clean package -DskipTests=true";
        String mavenCommand = winMavenCommand;

        ProcessBuilder processBuilder = new ProcessBuilder(winMavenCommand.split(" "));
        processBuilder.directory(new File(projectDir));

        Process process = processBuilder.start();

        // 读取命令的输出
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("命令执行结束，退出码：" + exitCode);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerate("E:\\PlanetProject\\code-generator\\code-generator-basic");
    }

}
