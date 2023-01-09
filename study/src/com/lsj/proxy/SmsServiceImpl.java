package com.lsj.proxy;

public class SmsServiceImpl implements SmsService {

    @Override
    public void sendMessage(String msg) {
        System.out.println("send message :" + msg);
    }

    @Override
    public String eat(String food) {
        System.out.println("eat food :" + food);
        return food;
    }

}
