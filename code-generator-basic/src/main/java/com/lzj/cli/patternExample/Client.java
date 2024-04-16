package com.lzj.cli.patternExample;

/**
 * 客户端（初始化设备对象、具体命令对象、遥控器对象，互相绑定，互相传递）
 */

public class Client {
    public static void main(String[] args) {
        // 电视（创建接收者对象）
        Device tv = new Device("TV");
        Device stereo = new Device("Stereo");

        // 电视的行为结果（创建具体命令对象，可以绑定不同设备）
        TurnOnCommand turnOn = new TurnOnCommand(tv);
        TurnOffCommand turnOff = new TurnOffCommand(stereo);

        // 遥控器（创建调用者）
        RemoteControl remote = new RemoteControl();

        // 按下遥控器（执行命令）
        remote.setCommand(turnOn);
        remote.pressButton();

        remote.setCommand(turnOff);
        remote.pressButton();
    }
}
