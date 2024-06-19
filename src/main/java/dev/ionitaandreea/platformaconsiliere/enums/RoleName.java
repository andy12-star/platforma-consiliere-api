package dev.ionitaandreea.platformaconsiliere.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleName {
    ROLE_USER ("role_user"), ROLE_DOCTOR("role_doctor"), ROLE_ADMIN ("role_admin");

    private final String value;

    RoleName(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
