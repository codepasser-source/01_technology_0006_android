package com.baishui.android;

public class NetOperator {

    public void execute(){
        try {
            Thread.sleep(5000);
            System.out.println("休眠完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
