package com.yhgc.api.enums;

public enum AppStatusEnum {
    NORMAL(0, "正常"),
    DELETE(1, "删除");

    private Integer code;

    private String name;

    AppStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
