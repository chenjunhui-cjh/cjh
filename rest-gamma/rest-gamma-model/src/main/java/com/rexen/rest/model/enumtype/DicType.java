package com.rexen.rest.model.enumtype;

public enum DicType {

    EVENT_PATIENT_EFFECT("1","A"),
    EVENT_FACTORS("2","B"),
    EVENT_PERFORMANCE("3","C"),
    EVENT_MEASURES("4","D"),
    EVENT_HANDLING_RESULT("5","E"),
    EVENT_HIDDEN_TROUBLE("6","F"),;

    private String code;

    private String name;

    DicType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
