package com.lzj.maker.generator;

import com.lzj.maker.generator.main.GenerateTemplate;

/**
 * 生成代码生成器
 */

public class MainGenerator extends GenerateTemplate {

    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputPath) {
        System.out.println("不要给我输出 dist 啦！！！");
    }
}
