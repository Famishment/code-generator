package com.lzj.cli.patternExample;

/**
 * 具体命令（相当于，遥控器的某个操作按钮）
 */

public class TurnOffCommand implements Command{

    private Device device;

    public TurnOffCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        // 设备关闭
        device.turnOff();
    }
}
