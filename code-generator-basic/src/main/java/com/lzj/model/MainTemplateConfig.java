package com.lzj.model;

import lombok.Data;

/**
 * 动态模板配置
 */
@Data
public class MainTemplateConfig {
    /**
     * 是否生成循环
     */
    private boolean loop;

    /**
     * 作者注释，并设置默认值
     */
    private String author = "Jerry";

    /**
     * 输出信息，并设置默认值
     */
    private String outputText = "输出结果：";
}
