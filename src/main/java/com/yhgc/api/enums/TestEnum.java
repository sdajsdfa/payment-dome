package com.yhgc.api.enums;

import lombok.Getter;


@Getter
public enum TestEnum {

    LOWS_TRAIN(0,  "[1,2,3,4,5,6]"),

    HIGH_TRAIN(1,  "[1,2,3,4,5,6]"),

    ACOUSTIC_WAVE(2, "[1,2,3,4,5,6]");

    /**
     * 枚举值码
     */
    private final Integer code;

    /**
     * 枚举描述
     */
    private final String message;


    //  构造方法
    TestEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
