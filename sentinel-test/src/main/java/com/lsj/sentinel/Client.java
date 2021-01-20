package com.lsj.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Component;

@Component
public class Client {

    @SentinelResource(value = "test.sentinel", blockHandler = "handle")
    public void se() {
    }

    public String handle() {
        System.out.println("hhhhhhhhh");
        return "liusijia";
    }

    public String helloError(String name, Throwable e){
        return "error,"+name;
    }
}
