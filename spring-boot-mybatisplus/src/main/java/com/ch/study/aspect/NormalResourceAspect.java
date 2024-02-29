package com.ch.study.aspect;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.fastjson.JSON;
import com.ch.study.common.CommonResponse;
import com.ch.study.common.CommonSession;
import com.ch.study.common.ResponseUtil;
import com.ch.study.common.ThreadLocalManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;

@Slf4j
@Component
@Aspect
public class NormalResourceAspect {

    private static final String FLOW_ERROR = "flow - Too many requests, please try again later";
    private static final String DEGRADE_ERROR = "degrade - Too many requests, please try again later";
    private static final String OTHER_ERROR = "other - System busy, please try again later.";

    @Pointcut("execution(* com.ch.study..*.*(..))")
    public void requestMappingPointcut() {
    }

    @Around("requestMappingPointcut()")
    public Object handleLimitFlow(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        // 检查是否存在@SentinelResource注解
        if (method.isAnnotationPresent(SentinelResource.class)) {
            // 如果存在，直接执行原方法
            return pjp.proceed();
        } else {
            // 对于没有注解的方法，应用默认的限流规则
            try (Entry entry = SphU.entry(method.getDeclaringClass().getName() + ":" + method.getName())) {
                // 被保护的业务逻辑0
                return pjp.proceed();
            } catch (BlockException ex) {
                // 处理被流控的逻辑
                return handleBlockException(ex);
            }
        }
    }

    /**
     * 异常处理
     */
    private ResponseEntity<CommonResponse> handleBlockException(BlockException ex) {
        if (ex instanceof FlowException) {
            log.warn("Blocked by Sentinel (flow) rule = {}", JSON.toJSONString(ex.getRule()));
            log.warn("Blocked by Sentinel (flow) cause = {}", JSON.toJSONString(ex.getCause()));
            log.warn("Blocked by Sentinel (flow) message = {}", JSON.toJSONString(ex.getMessage()));
            buildThreadLocal(FLOW_ERROR);
            // 处理流量控制异常
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ResponseUtil.success(FLOW_ERROR));
        } else if (ex instanceof DegradeException) {
            log.warn("Blocked by Sentinel (degrade) rule = {}", JSON.toJSONString(ex.getRule()));
            log.warn("Blocked by Sentinel (degrade) cause = {}", JSON.toJSONString(ex.getCause()));
            log.warn("Blocked by Sentinel (degrade) message = {}", JSON.toJSONString(ex.getMessage()));
            buildThreadLocal(DEGRADE_ERROR);
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ResponseUtil.success(DEGRADE_ERROR));
        } else {
            // 其他限流异常
            log.warn("Blocked by Sentinel (other) rule = {}", JSON.toJSONString(ex.getRule()));
            log.warn("Blocked by Sentinel (other) cause = {}", JSON.toJSONString(ex.getCause()));
            log.warn("Blocked by Sentinel (other) message = {}", JSON.toJSONString(ex.getMessage()));
            buildThreadLocal(OTHER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.success(OTHER_ERROR));
        }
    }

    /**
     * 设置上下文数据
     */
    private static void buildThreadLocal(String messageError) {
        CommonSession commonSession = new CommonSession();
        commonSession.setSentinelErrorMessages(Collections.singletonList(messageError));
        ThreadLocalManager.setValue(commonSession);
    }


    @After("execution(* com.ch.study..*.*(..))")
    public void afterRequest() {
        // 在请求后删除 ThreadLocal 的值
        ThreadLocalManager.removeValue();
    }

    @AfterThrowing(pointcut = "execution(* com.ch.study..*.*(..))", throwing = "ex")
    public void handleException(Exception ex) {
        ThreadLocalManager.removeValue();
    }

}
