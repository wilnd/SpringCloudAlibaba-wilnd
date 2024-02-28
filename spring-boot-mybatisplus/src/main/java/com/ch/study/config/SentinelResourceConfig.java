package com.ch.study.config;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Component
public class SentinelResourceConfig {

    private int status = 0;

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String nacosServerAddr;

    @Value("${spring.cloud.nacos.config.group}")
    private String nacosGroup;

    @Value("${spring.cloud.nacos.config.flow-data-id}")
    private String dataId;

    @Value("${spring.cloud.nacos.config.degrade-data-id}")
    private String degradeDataId;

    private static final Set<String> exludeResourceSet = new HashSet<>();

    static {
        exludeResourceSet.add("{ /error}");
        exludeResourceSet.add("{ /error, produces [text/html]}");
    }

    private final RequestMappingHandlerMapping handlerMapping;


    @Autowired
    public SentinelResourceConfig(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @PostConstruct
    public void init() {
        loadRulesFromNacos();
        loadDegradeRulesFromNacos();
        loadCustomerRules();
    }

    private void loadRulesFromNacos() {
        Converter<String, List<FlowRule>> parser = source -> JSON.parseArray(source, FlowRule.class);
        NacosDataSource<List<FlowRule>> nacosDataSource = new NacosDataSource<>(nacosServerAddr, nacosGroup, dataId, parser);
        log.info("loadRulesFromNacos:{}", JSON.toJSONString(nacosDataSource));
        FlowRuleManager.register2Property(nacosDataSource.getProperty());
    }

    private void loadDegradeRulesFromNacos() {
        Converter<String, List<DegradeRule>> degradeRuleParser = source -> JSON.parseArray(source, DegradeRule.class);
        NacosDataSource<List<DegradeRule>> degradeRuleDataSource = new NacosDataSource<>(nacosServerAddr, nacosGroup, degradeDataId, degradeRuleParser);
        log.info("loadDegradeRulesFromNacos:{}", JSON.toJSONString(degradeRuleDataSource));
        DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());
    }


    public void loadCustomerRules() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();

        List<FlowRule> flowRules = new ArrayList<>();
        List<DegradeRule> degradeRules = new ArrayList<>();


        for (RequestMappingInfo requestMappingInfo : handlerMethods.keySet()) {
            if (exludeResourceSet.contains(requestMappingInfo.toString())) {
                continue;
            }
            HandlerMethod handlerMethod = handlerMethods.get(requestMappingInfo);
            if (handlerMethod.getMethodAnnotation(RequestMapping.class) != null) {
                String resource = handlerMethod.getBeanType().getName() + ":" + handlerMethod.getMethod().getName();

                FlowRule flowRule = buildFlowRule(resource);
                flowRules.add(flowRule);

                DegradeRule degradeRule = buildDegradeRule(resource);
                degradeRules.add(degradeRule);
            }
        }

        FlowRuleManager.loadRules(flowRules);
        DegradeRuleManager.loadRules(degradeRules);
    }

    private DegradeRule buildDegradeRule(String resource) {
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource(resource);
        degradeRule.setCount(10); //异常数
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT); //异常作为熔断降级指标
        degradeRule.setTimeWindow(10); //时间窗口10s
        return degradeRule;
    }

    private FlowRule buildFlowRule(String resource) {
        FlowRule flowRule = new FlowRule();
        flowRule.setResource(resource);
        flowRule.setCount(1); //限流阈值 qps   20  个请求
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流阈值类型
        flowRule.setLimitApp("default");  //流控针对的调用来源，若为default则不区分调用来源
        return flowRule;
    }

}
