package com.example.demo.bean;

/**
 * 通过ThreadLocal 将数据源设置到每个线程上下文中
 */
public class DBContextHolder {
    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    public static void set(DBTypeEnum dbTypeEnum) {
        contextHolder.set(dbTypeEnum);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
        System.out.println("切换至master");
    }

    public static void slave() {
        set(DBTypeEnum.SLAVE);
        System.out.println("切换至slave");
    }
}
