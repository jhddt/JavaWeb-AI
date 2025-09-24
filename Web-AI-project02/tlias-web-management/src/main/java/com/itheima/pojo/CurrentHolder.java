package com.itheima.pojo;

/**
 * 当前用户持有者工具类
 * 用于在多线程环境中存储和获取当前操作用户的ID
 */
public class CurrentHolder {

    // 使用ThreadLocal存储当前线程的用户ID，确保线程安全
    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    /**
     * 设置当前线程的用户ID
     * @param employeeId 员工ID
     */
    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    /**
     * 获取当前线程的用户ID
     * @return 当前用户的员工ID
     */
    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }

    /**
     * 清除当前线程的用户ID
     */
    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
