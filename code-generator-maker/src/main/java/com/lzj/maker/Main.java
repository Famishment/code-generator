package com.lzj.maker;

//import com.lzj.maker.cli.CommandExecutor;
import com.lzj.maker.generator.MainGenerator;
import freemarker.template.TemplateException;
import java.io.IOException;

/**
 *  全局调用入口
 */

public class Main {

    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        //args = new String[] {"generate", "-l", "-a", "-o"};
        //args = new String[] {"config"};
        //args = new String[] {"list"};
        /*CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);*/
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();
    }

}
