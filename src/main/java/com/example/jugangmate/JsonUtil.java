package com.example.jugangmate;

import org.springframework.stereotype.Component;

@Component
public class JsonUtil {
    public RoleEnum RoleEnumConvert(String source) {
        switch (source) {
            case "MASTER":
                return RoleEnum.MASTER;
            case "CHEF":
                return RoleEnum.CHEF;
            case "GUEST":
                return RoleEnum.GUEST;
            default:
                throw new IllegalArgumentException("유효하지 않은 UserRole 값입니다: " + source);
        }
    }
}
