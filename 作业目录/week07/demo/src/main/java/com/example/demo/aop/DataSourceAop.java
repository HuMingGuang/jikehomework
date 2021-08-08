package com.example.demo.aop;

import com.example.demo.bean.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {
    /**
     * 不是master切点
     */
    @Pointcut("!@annotation(com.example.demo.annotation.Master)" +
            "&&(execution(* com.example.demo.service..*.select*(..))" +
            "||execution(* com.example.demo.service..*.get*(..)))")
    public void readPointcut() {
    }

    /**
     * master切点
     */
    @Pointcut("!@annotation(com.example.demo.annotation.Master)" +
            "|| execution(* com.example.demo.service..*.insert*(..))" +
            "||execution(* com.example.demo.service..*.add*(..))" +
            "||execution(* com.example.demo.service..*.edit*(..))" +
            "||execution(* com.example.demo.service..*.update*(..))" +
            "||execution(* com.example.demo.service..*.delete*(..))" +
            "||execution(* com.example.demo.service..*.remove*(..))")
    public void writePointcut() {
    }

    /**
     * 在读的切点前，将数据源切换为slave
     */
    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
}
