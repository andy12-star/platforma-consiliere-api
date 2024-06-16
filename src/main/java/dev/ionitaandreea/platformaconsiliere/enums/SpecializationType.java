package dev.ionitaandreea.platformaconsiliere.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SpecializationType {
    CONSILIERE_EDUCATIONALA ("consiliere educationala"), LIFE_COACHING ("life coaching"), ONLINE("psihoterapie online"), CONSILIERE_PROFESIONALA("consiliere profesionala"),
    CONSILIERE_PSIHOLOGICA("consiliere psihologica");

    private final String value;

    SpecializationType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
