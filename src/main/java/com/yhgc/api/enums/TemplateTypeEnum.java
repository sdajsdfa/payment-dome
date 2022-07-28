package com.yhgc.api.enums;


public enum TemplateTypeEnum {

    SINGLE("单桩模板", 0), ENGINEERING("工程模板", 1);

    //  成员变量
    private String name;
    private Integer code;

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }

    //  构造方法
    TemplateTypeEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }


    public static TemplateTypeEnum getTemplateType( Integer code ) {

        for( TemplateTypeEnum changeFlagEnum : TemplateTypeEnum.values() ) {

            if( code == changeFlagEnum.getCode()) {

                return changeFlagEnum;
            }
        }

        return ENGINEERING;
    }
}
