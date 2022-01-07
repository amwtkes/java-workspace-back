package com.xiaojin.algorithm.maxsequencingsublistsum.processors.base;

public enum SourceDataType {
    INT(1),
    FLOAT(1.0f);

    private SourceDataType(Object value) {
        this.value = value;
    }

    private final Object value;

    public Object getValue() {
        return this.value;
    }
}
