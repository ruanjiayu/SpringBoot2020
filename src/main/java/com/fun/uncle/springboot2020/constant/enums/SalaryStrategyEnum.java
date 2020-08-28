package com.fun.uncle.springboot2020.constant.enums;

/**
 * @Description: 枚举类方法
 * @Author: Summer
 * @DateTime: 2020/8/28 9:26 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public enum SalaryStrategyEnum {

    BOSS(0) {
        @Override
        double getSalary() {
            return 100000;
        }
    },
    LEADER(1) {
        @Override
        double getSalary() {
            return 50000;
        }
    },
    STAFF(2) {
        @Override
        double getSalary() {
            return 10000;
        }
    };

    private final int position;

    SalaryStrategyEnum(int position) {
        this.position = position;
    }

    abstract double getSalary();

    public static SalaryStrategyEnum valueOf(int position) {
        for (SalaryStrategyEnum salaryStrategyEnum : SalaryStrategyEnum.values()) {
            if (salaryStrategyEnum.position == position) {
                return salaryStrategyEnum;
            }
        }
        return null;
    }
}
