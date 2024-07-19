package com.lzj.maker.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * @Auther: lzj
 * @Date: 2024/7/14-07-14-19:48
 * @Description: com.lzj.maker.generator
 */

public class ScriptGenerator {

    public static void doGenerate(String outputPath, String jarPath) {
        // Linux 脚本
        StringBuilder sb = new StringBuilder();
        /*#!/bin/bash
        java -jar target/code-generator-basic-1.0-SNAPSHOT-jar-with-dependencies.jar "$@"*/
        sb.append("#!/bin/bash").append("\n");
        sb.append(String.format("java -jar %s \"$@\"", jarPath)).append("\n");
        FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8), outputPath);
        // 添加可执行权限
        try {
            Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrwxrwx");
            Files.setPosixFilePermissions(Paths.get(outputPath), permissions);
        } catch (Exception e) {

        }

        // Windows 脚本
        sb = new StringBuilder();
        /*@echo off
        java -jar target/code-generator-basic-1.0-SNAPSHOT-jar-with-dependencies.jar %**/
        sb.append("@echo off").append("\n");
        sb.append(String.format("java -jar %s %%*", jarPath)).append("\n");
        FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8), outputPath + ".bat");
    }

}
