package com.example.camel.model;

public enum Pincode {
    ELC(560100),
    WHITE_FIELD(560101),
    MG_ROAD(560102),
    MADHUBAN(845420);

    private final int value;

    Pincode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
