package com.lsj.sentinel;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class SentinelConfig {

    private String resourceName = "test";

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    @PostConstruct
    private void initRules() {
        // 限流规则
        List<FlowRule> flowRules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource(resourceName);
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 每秒调用最大次数为 1
        flowRule.setCount(1);
        flowRules.add(flowRule);

        // 降级规则
        List<DegradeRule> degradeRules = new ArrayList<>();
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource(resourceName);
        // set threshold rt, 10 ms
        degradeRule.setCount(10);
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        degradeRule.setTimeWindow(10);
        degradeRules.add(degradeRule);

        ParamFlowRule paramFlowRule = new ParamFlowRule(resourceName)
                .setParamIdx(0)
                .setCount(5);
        // 针对 int 类型的参数 PARAM_B，单独设置限流 QPS 阈值为 10，而不是全局的阈值 5.
//        ParamFlowItem item = new ParamFlowItem().setObject(String.valueOf(PARAM_B))
//                .setClassType(int.class.getName())
//                .setCount(10);
//        paramFlowRule.setParamFlowItemList(Collections.singletonList(item));

        // 将控制规则载入到 Sentinel
        //FlowRuleManager.loadRules(flowRules);
        DegradeRuleManager.loadRules(degradeRules);
        ParamFlowRuleManager.loadRules(Collections.singletonList(paramFlowRule));

    }
}
