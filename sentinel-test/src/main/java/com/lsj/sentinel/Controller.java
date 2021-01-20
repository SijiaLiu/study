package com.lsj.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lsj.sentinel.util.ExceptionHandle;
import com.lsj.sentinel.util.GetIpAndPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private Client client;

    @Autowired
    private Count count;

    @RequestMapping("/hello")
    @SentinelResource(value = "test", blockHandler = "handleString", blockHandlerClass = ExceptionHandle.class, fallback = "helloError", entryType = EntryType.IN)
    public String hello(@RequestParam(value = "name") String name, @RequestParam(value = "age") Integer age) {
        //doSomething(name);
        //System.out.println(GetIpAndPort.getIpAndPort());
        return name;
    }

    private void doSomething(String name) {
        Entry entry = null;
        try {
            entry = SphU.entry("test", EntryType.IN, 1, name);
            Thread.sleep(100);
        } catch (BlockException e) {
            System.out.println("BlockException");
            e.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Exception");
            Tracer.traceEntry(ex, entry);
        } finally {
            if (entry != null) {
                entry.exit(1, name);
            }
        }


    }

    /**
     * 参数和返回值必须和限流方法一致 再加一个 BlockException
     *
     * @param name
     * @param e
     * @return
     */
    public String handle(String name, Integer age, BlockException e) {
        System.out.println("BlockException");
        System.out.println(e);
        return "liusijia";
    }

    /**
     * 参数和返回值必须和限流方法一致 再加一个 Throwable
     *
     * @param name
     * @param e
     * @return
     */
    public String helloError(String name, int age, Throwable e) {
        System.out.println("Throwable");
        System.out.println(e);
        return "error";
    }

}
