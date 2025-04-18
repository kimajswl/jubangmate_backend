package com.example.jugangmate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {
    MASTER("MASTER", 0),
    CHEF("CHEF", 1),
    GUEST("GUEST", 2);

    private final String userRole;
    private final int roleId;
}
