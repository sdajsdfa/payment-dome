package com.yhgc.api.enums;

public enum StatusEnum {
    NORMAL(0, "正常"),
    DEACtTIVAT(1, "停用"),
    DELETE(2, "删除");

    private Integer code;

    private String name;

    StatusEnum(Integer code, String name) {
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

    /**
     * 根据属性name获取属性code:
     * 比如根据 "正常" 得到 0
     * @param name
     * @return
     */
    public static Integer getCodeByName(String name) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            String n = statusEnum.getName();
            if (n.equals(name)) {
                return statusEnum.getCode();
            }
        }
        return null;
    }

    /**
     * 根据属性code获取属性name:
     * 比如根据 0 得到 "正常"
     * @param code
     * @return
     */
    public static String getNameByCode(Integer code) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            Integer c = statusEnum.getCode();
            if (c == code) {
                return statusEnum.getName();
            }
        }
        return null;
    }
}
