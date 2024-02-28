package com.ch.study.config;

import cn.hutool.core.collection.CollectionUtil;
import com.ch.study.common.ResponseUtil;
import com.ch.study.common.ThreadLocalManager;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        // 处理所有类型的异常，并返回统一的响应
        String errorMessage = "Internal Server Error";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        // 处理自定义的 NotFoundException 异常，并返回对应的响应
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<Object> handleClassCastException(ClassCastException ex) {
        // 处理自定义的 NotFoundException 异常，并返回对应的响应
        List<String> sentinelErrorMessages = ThreadLocalManager.getValue().getSentinelErrorMessages();
        if (CollectionUtil.isNotEmpty(sentinelErrorMessages)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ResponseUtil.fail(sentinelErrorMessages.get(0)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
