package com.lzj.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.lzj.model.MainTemplateConfig;
import picocli.CommandLine.Command;
import java.lang.reflect.Field;

/**
 * 子命令 - config
 */

@Command(name = "config", description = "查看参数信息", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {

    @Override
    public void run() {
        System.out.println("查看参数信息：");
        // 根据反射机制，获取类的属性/字段
        // 方法一：JDK原生反射语法
        /*Class<?> myClass = MainTemplateConfig.class;
        Field[] fields = myClass.getDeclaredFields();*/
        // 方法二：Hutool工具类的方法
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);
        for (Field field : fields) {
            System.out.println("字段名称：" + field.getName());
            System.out.println("字段类型：" + field.getType());
            System.out.println("--------");
        }
    }

}
