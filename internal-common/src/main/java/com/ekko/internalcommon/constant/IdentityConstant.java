package com.ekko.internalcommon.constant;

import lombok.Getter;

public enum IdentityConstant {
    PASSENGER(1, "passenger"),

    DRIVER(2, "driver")
    ;

    @Getter
    private int code;

    @Getter
    private String identity;

    IdentityConstant(int code, String identity) {
        this.code = code;
        this.identity = identity;
    }
}
