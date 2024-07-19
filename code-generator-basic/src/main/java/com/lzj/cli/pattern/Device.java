package com.lzj.cli.pattern;

/**
 * 接收者（相当于，被遥控的设备，电视）
 */

public class Device {

    private String name;

    public Device(String name) {
        this.name = name;
    }

     public void turnOn() {
         System.out.println(name + "设备打开");
     }

     public void turnOff() {
         System.out.println(name + "设备关闭");
     }

}
