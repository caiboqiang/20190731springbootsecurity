package com.cai.config;


import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 声明规则监听器
 */
@Component
public class SentinelConfig  implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        //声明规则
        FlowRule  flowRule = new FlowRule();
        flowRule.setRefResource("Serve_6015");//设置资源
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);//设置流控规则类型
        flowRule.setCount(1);//每秒1个请求
        List<FlowRule> list = new ArrayList<FlowRule>();
        list.add(flowRule);
        FlowRuleManager.loadRules(list);
    }
}
